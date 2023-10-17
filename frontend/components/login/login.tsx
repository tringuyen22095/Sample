// "use client";

// import React, { useState, useEffect } from 'react';
// import "./login.scss";


// const Login: React.FC = () => {
//   const [formFields, setFormFields] = useState([]);

//   useEffect(() => {

//   }, []);

//   const addField = () => {
//     const newField = {
//       type: 'text',
//       label: 'New Field',
//     };
//     setFormFields([...formFields, newField]);
//   };

//   const removeField = (index: number) => {
//     const updatedFields = [...formFields];
//     updatedFields.splice(index, 1);
//     setFormFields(updatedFields);
//   };

//   const renderFields = () => {
//     return formFields.map((field: any, index: number) => (
//       <div key={index}>
//         <input type={field.type} placeholder={field.label} />
//         <button onClick={() => removeField(index)}>Remove</button>
//       </div>
//     ));
//   };

//   return (
//     <div className='login'>

//     </div>
//   );
// };

// export default Login;
