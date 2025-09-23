package org.Algorix.TallerKinal.web.exception;

import org.Algorix.TallerKinal.dominio.exception.*;
import org.Algorix.TallerKinal.dominio.exception.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {
        Error error = new Error("error_desconocido", ex.getMessage());
        return  ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex) {
        List<Error> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errores.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

    // Nuevo: manejar parseo inválido del enum MarcaVehiculo
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleException(HttpMessageNotReadableException ex) {
        String msg = ex.getMessage();
        if (msg != null && msg.contains("MarcaVehiculo")) {
            return ResponseEntity.badRequest().body(new Error(
                    "marca_no_existe",
                    "La marca no existe. Debe ingresarla en mayúsculas."
            ));
        }
        return ResponseEntity.badRequest().body(new Error("mensaje_no_legible", ex.getMessage()));
    }

    @ExceptionHandler(MarcaVehiculoNoExiste.class)
    public ResponseEntity<Error> handleException(MarcaVehiculoNoExiste ex) {
        return ResponseEntity.badRequest().body(new Error("marca_no_existe", ex.getMessage()));
    }

    @ExceptionHandler(CategoriaNoExiste.class)
    public ResponseEntity<Error> handleException(CategoriaNoExiste ex) {
        Error error = new Error("categoria_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CitaCancelada.class)
    public ResponseEntity<Error> handleException(CitaCancelada ex) {
        Error error = new Error("cita_cancelada", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ClienteNoExiste.class)
    public ResponseEntity<Error> handleException(ClienteNoExiste ex) {
        Error error = new Error("cliente_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ContrasenaInvalida.class)
    public ResponseEntity<Error> handleException(ContrasenaInvalida ex) {
        Error error = new Error("contrasena_invalida", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CorreoDuplicado.class)
    public ResponseEntity<Error> handleException(CorreoDuplicado ex) {
        Error error = new Error("correo_duplicado", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CorreoInvalido.class)
    public ResponseEntity<Error> handleException(CorreoInvalido ex) {
        Error error = new Error("correo_invalido", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MarcaNoExiste.class)
    public ResponseEntity<Error> handleException(MarcaNoExiste ex) {
        Error error = new Error("marca_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ProveedorNoExiste.class)
    public ResponseEntity<Error> handleException(ProveedorNoExiste ex) {
        Error error = new Error("proveedor_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(StockInsuficiente.class)
    public ResponseEntity<Error> handleException(StockInsuficiente ex) {
        Error error = new Error("stock_insuficiente", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(VehiculoNoExiste.class)
    public ResponseEntity<Error> handleException(VehiculoNoExiste ex) {
        Error error = new Error("vehiculo_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(TelefonoInvalido.class)
    public ResponseEntity<Error> handleException(TelefonoInvalido ex) {
        Error error = new Error("telefono_invalido", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(VehiculoDuplicadoPlaca.class)
    public ResponseEntity<Error> handleException(VehiculoDuplicadoPlaca ex) {
        Error error = new Error("vehiculo_duplicado_placa", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CitaNoExiste.class)
    public ResponseEntity<Error> handleException(CitaNoExiste ex) {
        Error error = new Error("cita_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(AdministradorNoExiste.class)
    public ResponseEntity<Error> handleException(AdministradorNoExiste ex) {
        Error error = new Error("administrador_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MecanicoNoExiste.class)
    public ResponseEntity<Error> handleException(MecanicoNoExiste ex) {
        Error error = new Error("mecanico_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ProductoNoExiste.class)
    public ResponseEntity<Error> handleException(ProductoNoExiste ex) {
        Error error = new Error("producto_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(TrabajoNoExiste.class)
    public ResponseEntity<Error> handleException(TrabajoNoExiste ex) {
        Error error = new Error("trabajo_no_existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
