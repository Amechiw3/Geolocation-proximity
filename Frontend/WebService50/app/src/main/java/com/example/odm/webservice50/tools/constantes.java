package com.example.odm.webservice50.tools;
/**
 * Created by odm on 7/27/2016.
 */
public class constantes {
    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;
    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "";
    /**
     * Dirección IP de genymotion o AVD
     */
    private static final String IP = "192.168.1.3";
    /**
     * URLs del Web Service
     */

    public static final String GET = "http://" + IP + PUERTO_HOST + "/webservice/obtener_usuarios.php";
    public static final String GET_BY_ID = "http://" + IP + PUERTO_HOST + "/webservice/obtener_usuarios_por_id.php";
    public static final String UPDATE = "http://" + IP + PUERTO_HOST + "/webservice/actualizar_usuarios.php";
    public static final String DELETE = "http://" + IP + PUERTO_HOST + "/webservice/borrar_usuarios.php";
    public static final String INSERT = "http://" + IP + PUERTO_HOST + "/webservice/insertar_usuarios.php";

    /**
     * Clave para el valor extra que representa al identificador de una meta
     */
    public static final String EXTRA_ID = "IDEXTRA";
}
