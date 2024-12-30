public class JDBCService implements Closeable {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCService.class);
    private final ChronicleQueue in;
    private final ChronicleQueue out;
    private final ExecutorService service;
    private final ThrowingSupplier<Connection, SQLException> connectionSupplier;
    private volatile boolean closed = false;

    public JDBCService(ChronicleQueue in, ChronicleQueue out, ThrowingSupplier<Connection, SQLException> connectionSupplier) throws SQLException {
        this.in = in;
        this.out = out;
        this.connectionSupplier = connectionSupplier;

        service = Executors.newSingleThreadExecutor(
                new NamedThreadFactory(in.file().getName() + "-JDBCService", true)); (1)
        service.execute(this::runLoop); (2)
        service.shutdown(); // stop when the task exits.
    }


    void runLoop() {
        try {
                    .methodWriterBuilder(JDBCResult.class)
            JDBCResult result = out.createAppender() (3)
                    .recordHistory(true)
                    .get();
            JDBCComponent js = new JDBCComponent(connectionSupplier, result);
            MethodReader reader = in.createTailer().afterLastWritten(out).methodReader(js); (4)
            Pauser pauser = new LongPauser(50, 200, 1, 10, TimeUnit.MILLISECONDS);
            while (!closed) {
                if (reader.readOne()) (5)
                    pauser.reset();
                else
                    pauser.pause();
            }
        } catch (Throwable t) {
            LOGGER.error("Run loop exited", t);
        }
    }

    @Override
    public void close() {
        closed = true;
    }

    public JDBCStatement createWriter() {
        return in.createAppender() (6)
                .methodWriterBuilder(JDBCStatement.class)
                .recordHistory(true)
                .get();
    }

    public MethodReader createReader(JDBCResult result) {
        return out.createTailer().methodReader(result);
    }
}
