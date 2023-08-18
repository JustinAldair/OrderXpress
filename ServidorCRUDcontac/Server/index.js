const express = require('express');
const userRoutes = require('./routes/userRoutes');
const nodemailer = require('nodemailer');

const PORT = process.env.PORT || 3000;

const app = express();
app.use(express.json());
app.use(userRoutes);

// Configuración del transporte de correo
let transporter = nodemailer.createTransport({
  host: "smtp.gmail.com",
  port: 465,
  secure: true,
  auth: {
      user: "pedidoagil20@gmail.com",
      pass: "myrtyjeoyjtbiarn",
  },
});

// Ruta para enviar el correo de verificación
app.get('/verify-email', (req, res) => {
  const email = "jonaex.84b@gmail.com";
  const verificationCode = generateVerificationCode(); // Genera el código de verificación

  // Configuración del correo electrónico
  const mailOptions = {
    from: 'pedidoagil20@gmail.com',
    to: email,
    subject: 'Verificación de correo electrónico',
    text: `Tu código de verificación es: ${verificationCode}`
  };

  // Envío del correo electrónico
  transporter.sendMail(mailOptions, (error, info) => {
    if (error) {
      console.error('Error al enviar el correo electrónico:', error);
      res.status(500).send('Error al enviar el correo electrónico de verificación');
    } else {
      console.log('Correo electrónico de verificación enviado:', info.messageId);
      res.send('Correo electrónico de verificación enviado exitosamente');
    }
  });
});

app.listen(PORT, () => {
  console.log(`Server listening on http://localhost:${PORT}`);
});

function generateVerificationCode() {
  const characters = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
  const codeLength = 6;
  let verificationCode = '';

  for (let i = 0; i < codeLength; i++) {
    const randomIndex = Math.floor(Math.random() * characters.length);
    verificationCode += characters.charAt(randomIndex);
  }

  return verificationCode;
}