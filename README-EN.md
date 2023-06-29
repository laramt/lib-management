<h1 align="center">📚<br>Library Management</h1>
<p align="center">
This project is a library management system developed in Java/Spring.
</p>
<p align="center">
  <a href="https://www.java.com">
    <img src="https://img.shields.io/badge/java-17-yellow.svg">
  </a>
  <a href="https://spring.io/">
    <img src="https://img.shields.io/badge/Spring-3.0.6-green.svg">
  </a>
</p>

For the Portuguese version, please refer to the [README.md](./README.md) file.

## Functionalities

- Registration and management of books, authors, publishers, and users
- Book search and filtering
- Loan and devolution of books
- Sending automatic emails to notify users about loans, devolutions, etc.
<br></br>

## Installation

1. Clone the repository:

   ```bash
   git clone https://https://github.com/laramt/lib-management.git
   ```

2. Import the project into your preferred IDE.

3. Configure environment variables.

3. Configure the application.properties file to include the required SMTP settings.

4. Run the project in your IDE.
<br></br>

## Settings

To send automatic emails, you need the SMTP settings in the ``application.properties`` file.

```` properties
# EMAIL
spring.mail.host= your_host_smtp
spring.mail.port=your_smtp_port
spring.mail.username=your_email
spring.mail.password=your_password
spring.mail.properties.mail.smtp.auth= true
spring.mail.properties.mail.smtp.starttls.enable= true
 ````
Replace the values ​​your_smtp_host, your_smtp_port, your_email and your_password with your specific SMTP server settings. Make sure you have the proper credentials and SMTP server information from your email service provider.
<br></br>

## Contact

If you have any questions or comments, please feel free to reach out [here](mailto:laramnckt@gmail.com).