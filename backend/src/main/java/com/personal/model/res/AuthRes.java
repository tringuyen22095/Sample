package com.personal.model.res;

public class AuthRes {

	private String token;
	private String refreshToken;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String token;
		private String refreshToken;
		
		public Builder() {
			this.token = "";
			this.refreshToken = "";
		}
		
		public Builder withToken(final String token) {
			this.token = token;
			return this;
		}
		
		public Builder withRefreshToken(final String refreshToken) {
			this.refreshToken = refreshToken;
			return this;
		}
		
		public AuthRes build() {
			AuthRes authRes = new AuthRes();
			authRes.setToken(this.token);
			authRes.setRefreshToken(this.refreshToken);
			return authRes;
		}
	}
}
