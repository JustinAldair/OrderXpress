const db = require('../config/connection');
const multer = require('multer');
const bcrypt = require('bcrypt');

function getUserById(id, callback) {
  console.log(id);
  const query = 'SELECT user.idUser, user.nameUser, user.email, user.phone, user.dateRegister, category.categoria, GROUP_CONCAT(skill.nameSkill) AS skills, user.routesPhoto FROM user LEFT JOIN category ON user.id_category = category.id_category LEFT JOIN skill ON user.idUser = skill.id_user  WHERE user.idUser = 13 GROUP BY user.idUser;';
  db.query(query, [id], callback);
}

function login(email, password, callback) {
  const query = 'SELECT idUser, password FROM user WHERE email = ?';
  db.query(query, [email], (error, results) => {
    if (error) {
      console.error('Error al realizar el inicio de sesión: ', error);
      callback(error, null);
    } else if (results.length === 0) {
      callback(false, null);
    } else {
      const storedPassword = results[0].password;
      bcrypt.compare(password, storedPassword, (error, isMatch) => {
        if (error) {
          console.error('Error al realizar el inicio de sesión: ', error);
          callback(error, null);
        } else if (isMatch) {
          const user = {
            idUser: results[0].idUser,
          };
          callback(null, user);
        } else {
          callback(false, null);
        }
      });
    }
  });
}

function createUser(user, callback) {
  // Generar un hash de la contraseña
  bcrypt.hash(user.password, 10, (error, hashedPassword) => {
    if (error) {
      callback(error, null);
      return;
    }

    const query = 'INSERT INTO user (nameUser, email, password, phone, dateRegister, id_category, routesPhoto) VALUES (?, ?, ?, ?, ?, ?, "uploads/defaultImage.png")';

    const values = [user.nameUser, user.email, hashedPassword, user.phone, user.dateRegister, user.id_category];

    db.query(query, values, (error, result) => {
      if (error) {
        callback(error, null);
      } else {
        callback(null, result.insertId);
      }
    });
  });
}


function checkUserExistence(email, callback) {
  const query = 'SELECT * FROM user WHERE email = ?';
  db.query(query, [email], (error, result) => {
    if (error) {
      console.error('Error al verificar la existencia del usuario: ', error);

    } else {
      const userExists = result.length > 0;
      callback(null, userExists, email)
    }
  });
}


// Configurar Multer para guardar las imágenes en una carpeta específica
function saveImage(destinationPath) {
  const storage = multer.diskStorage({
    destination: function (req, file, cd) {
      cd(null, destinationPath);
    },
    filename: function (req, file, cd) {
      cd(null, file.originalname);
      console.log(file.originalname);
    }
  });

  return multer({ storage: storage }).single('file');
}

//////Contactos:
//Crear Contacto
function createContac(contac, callback) {
  const query = 'INSERT INTO contacto (Nombre, Telefono, Correo, Direccion) VALUES (?, ?, ?, ?)';
  const values = [contac.Nombre , parseInt(contac.Telefono), contac.Correo, contac.Direccion];


  db.query(query, values, (error, result) => {
    if (error) {
      callback(error, null);
    } else {
      // Aquí puedes devolver el ID del usuario recién insertado o cualquier otro valor que desees
      callback(null, result.id_Contacto);
    }
  });
}

function updateContac(id, contac, callback) {
  const query = 'UPDATE contacto SET Nombre = ?, Telefono = ?, Correo = ?, Direccion = ? WHERE id_Contacto = ?';
  const values = [contac.Nombre, parseInt(contac.Telefono), contac.Correo, contac.Direccion, id];

  db.query(query, values, (error, result) => {
    if (error) {
      callback(error, null);
    } else {
      // Aquí puedes devolver cualquier resultado que desees
      callback(null, result);
    }
  });
}




//Eliminar por id el contacto :
function deleteContac(id, callback) {
  const query = 'DELETE FROM contacto WHERE id_Contacto = ?';
  const values = [id];

  db.query(query, values, (error, result) => {
    if (error) {
      callback(error, null);
    } else {
      // Aquí puedes devolver cualquier resultado que desees
      callback(null, result);
    }
  });
}

//Optener contacto 
function getContacById(id, callback) {

  const query = 'SELECT * FROM contacto WHERE id_Contacto = ?';
  const values = [id];

  db.query(query, values, (error, result) => {
    if (error) {
      callback(error, null);
    } else {
      // Aquí puedes devolver el resultado de la consulta
      callback(null, result);
    }
  });
}



module.exports = {
  getUserById,
  login,
  createUser,
  checkUserExistence,
  saveImage,


   //Modulos para contacto
   createContac,
   getContacById,
   updateContac,
   deleteContac
};