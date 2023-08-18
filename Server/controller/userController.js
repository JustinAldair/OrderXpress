const userService = require('../service/service');
const resource = require('../controller/getResources/resources');
const multer = require('multer');

function getUserById(req, res) {
    const { id_user } = req.params;
    //console.log(id_user);
    userService.getUserById(id_user, (error, results) => {
        if (error) {
            console.error('Error al obtener el usuario: ', error);
            res.status(500).send('Error al obtener el usuario');
        } else if (results.length === 0) {
            res.status(404).send('Usuario no encontrado');
        } else {
            const user = results[0];
            res.json(user);
        }
    });
}


function login(req, res) {
    const { email, password } = req.body;

    // Verificar si el email y la contraseña coinciden en la base de datos
    userService.login(email, password, (error, user) => {
        if (error) {
            console.error('Error al realizar el inicio de sesión: ', error);
            res.status(500).send('Error al realizar el inicio de sesión');
        } else if (!user) {
            res.status(401).send('Credenciales inválidas');
        } else {
            res.send({
                message: 'Inicio de sesión exitoso',
                user: {
                    idUser: user.idUser,
                }
            });
        }
    });
}

function createUser(req, res) {
    const user = req.body;
    console.log(user);
    userService.createUser(user, (error, results) => {
        if (error) {
            console.error('Error al crear el usuario', error);
            res.status(500).send('Error al crear el usuario');
        } else {
            res.send('Usuario creado correctamente');
        }
    });
}


function checkUserExistence(req, res) {
    const { email } = req.body;

    userService.checkUserExistence(email, (error, userExists) => {
        if (error) {
            console.log('Error al verificar la existencia del usuario: ', error);
            res.status(500).send('Error al verificar la existencia del usuario');
        } else {
            resource.setEmail(email);
            console.log('Correo electrónico existente:', email);
            res.send({ userExists, email });
        }
    });
}

function uploadImage(req, res, destinationPath) {
    const upload = userService.saveImage(destinationPath);
  
    upload(req, res, function (err) {
      if (err) {
        res.status(500).send('Error al guardar la imagen');
        console.log('Error al guardar la imagen');
      } else {
        if (req.file) {
          res.status(200).send('Imagen guardada correctamente');
          console.log('Imagen guardada correctamente');
        } else {
          res.status(400).send('No se envió ninguna imagen');
          console.log('No se envió ninguna imagen');
        }
      }
    });
  }


  //rutas para modificar las rutas:
  function createRoute(req, res) {
    const ruta = req.body;
    userService.createRoute(ruta, (error, result) => {
      console.log("si estoy llegando")
      if(error){
        console.log(error)
        res.status(500).send('Error al crear la ruta');
      }else{
        res.status(200).json(ruta)
  
      }
    })
  }
  
  function updateRoute(req, res) {
    const { id } = req.params;
    const ruta = req.body;
    userService.updateRoute(id, ruta, (error, result) => {
      if (error) {
        console.error('Error al actualizar la ruta', error);
        res.status(500).send('Error al actualizar la ruta');
      } else {
        res.status(200).send('Ruta actualizada correctamente');
      }
    });
  }
  
  function deleteRoute(req, res) {
    const { id } = req.params;
    userService.deleteRoute(id, (error, result) => {
      if (error) {
        console.error('Error al eliminar la ruta', error);
        res.status(500).send('Error al eliminar la ruta');
      } else {
        res.status(200).send('Ruta eliminada correctamente');
      }
    });
  }
  
  function getRouteById(req, res) {
    const { id } = req.params;
  
    // Lógica para obtener la ruta por su ID desde el servicio
    userService.getRouteById(id, (error, result) => {
      if (error) {
        console.error('Error al obtener la ruta: ', error);
        res.status(500).send('Error al obtener la ruta');
      } else if (!result) {
        res.status(404).send('Ruta no encontrada');
      } else {
        res.json(result);
      }
    });
  }

  //fucniones para Contactos
function createContac(req, res) {
  const contac = req.body;
  userService.createContac(contac, (error, result) => {
    console.log("si, aqui estoy, soy el contacto :'v")
    if(error){
      console.log(error)
      res.status(500).send('Error al crear al contacto :c');
    }else{
      res.status(200).json(contac)

    }
  })
}

function updateContac(req, res) {
  const { id } = req.params;
  const contac = req.body;
  userService.updateContac(id, contac, (error, result) => {
    if (error) {
      console.error('Error al actualizar la informacion de contacto', error);
      res.status(500).send('Error al actualizar la informacion de contacto');
    } else {
      res.status(200).send('Informacion del Contacto Actualizada correctamente');
    }
  });
  
}

function deleteContac(req, res) {
  const { id } = req.params;
  userService.deleteContac(id, (error, result) => {
    if (error) {
      console.error('Error al eliminar al contacto', error);
      res.status(500).send('Error al eliminar al contacto');
    } else {
      res.status(200).send('Contacto eliminada correctamente');
    }
  });
}

function getContacById(req, res) {
  const { id } = req.params;

  // Lógica para obtener al contacto por su ID desde el servicio
  userService.getContacById(id, (error, result) => {
    if (error) {
      console.error('Error al obtener al contacto: ', error);
      res.status(500).send('Error al obtener al contacto');
    } else if (!result) {
      res.status(404).send('Contacto no encontrado');
    } else {
      res.json(result);
    }
  });
}
  

module.exports = {
    getUserById,
    login,
    createUser,
    checkUserExistence,
    uploadImage,

    //Rutas para el segundo CRUD

    getRouteById,
    createRoute,
    updateRoute,
    deleteRoute,

    //Modulos para contacto
  createContac,
  getContacById,
  updateContac,
  deleteContac
};