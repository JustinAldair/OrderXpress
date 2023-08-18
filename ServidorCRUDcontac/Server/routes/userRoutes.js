const express = require('express');
const router = express.Router();
const controller = require('../controller/userController');

router.get('/user/:id_user', function (req, res, next) {
    // Requerir el controlador aquí dentro de la función de enrutamiento
    const userController = require('../controller/userController');
    userController.getUserById(req, res, next);
});

router.post('/login', function (req, res, next) {
    console.log("Login")
    // Requerir el controlador aquí dentro de la función de enrutamiento
    const userController = require('../controller/userController');
    userController.login(req, res, next);
});

router.post('/userCreate', function (req, res, next){
    console.log("Nuevo Usuario")
    const userController = require('../controller/userController');
    userController.createUser(req, res, next);
});

router.post('/rutaCreate', controller.createRoute)
router.get('/ruta/:id', controller.getRouteById);
router.put('/ruta/:id', controller.updateRoute);
router.delete('/ruta/:id', controller.deleteRoute);


//Rutas para contacto:
router.post('/contacCreate', controller.createContac)
router.get('/contacGet/:id', controller.getContacById);
router.put('/contacUp/:id', controller.updateContac);
router.delete('/contac/:id', controller.deleteContac);

module.exports = router;