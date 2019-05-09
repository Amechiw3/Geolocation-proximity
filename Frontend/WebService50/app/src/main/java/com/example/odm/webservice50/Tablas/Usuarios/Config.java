package com.example.odm.webservice50.Tablas.Usuarios;

/**
 * Created by odm on 7/30/2016.
 */
public class Config {
    // Address of our scripts of the CRUD                50.62.209.162 192.168.1.4

    public static final String IP = "wbservices.16mb.com";

    public static final String URL_ADD          = "http://" + IP + "/WebService50/usuarios/addUser.php";
    public static final String URL_GET_ALL      = "http://" + IP + "/WebService50/usuarios/getAllUser.php";
    public static final String URL_GET_USER     = "http://" + IP + "/WebService50/usuarios/getUser.php?id_usu=";
    public static final String URL_GET_USER_ID  = "http://" + IP + "/WebService50/usuarios/getUserID.php?id_usu=";
    public static final String URL_UPDATE_USER  = "http://" + IP + "/WebService50/usuarios/updateUser.php";
    public static final String URL_DELETE_USER  = "http://" + IP + "/WebService50/usuarios/deleteUser.php?id_usu=";
    public static final String URL_LOGIN        = "http://" + IP + "/WebService50/usuarios/login.php";
    //  Brodcast
    public static final String URL_ADD_BROAD                = "http://" + IP + "/WebService50/broadcast/addBroadcast.php";
    public static final String URL_GET_ALL_BROAD            = "http://" + IP + "/WebService50/broadcast/getAllBroadcast.php";
    public static final String URL_DELETE_BROAD             = "http://" + IP + "/WebService50/broadcast/deleteBroadcast.php?id_brod=";
    public static final String URL_GET_BROAD                = "http://" + IP + "/WebService50/broadcast/getBroadcast.php?id_brod=";
    public static final String URL_UPDATE_BROAD             = "http://" + IP + "/WebService50/broadcast/updateBroadcast.php";
    public static final String URL_GET_ALL_BROAD_GPS        = "http://" + IP + "/WebService50/broadcast/getAllBroadcastGPS.php?";
    public static final String URL_GET_ALL_BROAD_GPSCustom  = "http://" + IP + "/WebService50/broadcast/getAllBroadcastGPSCustom.php?";
    //String URL_GPS = "http://192.168.0.4/WebService50/broadcast/getAllBroadcastGPS.php?posx=" + lat + "&posy=" + lon;
    // Chat Publico
    public static final String URL_ADD_CHPublico        = "http://" + IP + "/WebService50/chpublico/addCHPublico.php";
    public static final String URL_GET_ALL_CHPublico    = "http://" + IP + "/WebService50/chpublico/getAllCHPublico.php";
    public static final String URL_GET_ALL_CHPublicoGPS = "http://" + IP + "/WebService50/chpublico/getAllCHPublicoGPS.php";
    public static final String URL_DELETE_CHPublico     = "http://" + IP + "/WebService50/chpublico/deleteCHPublico.php?id_chpu=";
    public static final String URL_GET_CHPublico        = "http://" + IP + "/WebService50/chpublico/getCHPublico.php?id_chpu=";
    public static final String URL_UPDATE_CHPublico     = "http://" + IP + "/WebService50/chpublico/updateCHPublico.php";
    // Comentarios
    public static final String URL_ADD_Comentario       = "http://" + IP + "/WebService50/comentarios/addComentario.php";
    public static final String URL_GET_ALL_Comentario   = "http://" + IP + "/WebService50/comentarios/getAllComentario.php";
    public static final String URL_DELETE_Comentario    = "http://" + IP + "/WebService50/comentarios/deleteComentario.php?id_com=";
    public static final String URL_GET_Comentario       = "http://" + IP + "/WebService50/comentarios/getComentario.php?id_com=";
    public static final String URL_UPDATE_Comentario    = "http://" + IP + "/WebService50/comentarios/updateComentario.php";
    //Chat Privados
    public static final String URL_ADD_CHPrivado        = "http://" + IP + "/WebService50/chprivado/addCHPrivado.php";
    public static final String URL_ADD_CHPrivadoPr      = "http://" + IP + "/WebService50/chprivado/addCHPrivadoPrueba.php";
    public static final String URL_GET_ALL_CHPrivado    = "http://" + IP + "/WebService50/chprivado/getAllCHPrivado.php";
    public static final String URL_DELETE_CHPrivado     = "http://" + IP + "/WebService50/chprivado/deleteCHPrivado.php?id_chpr=";
    public static final String URL_GET_CHPrivado        = "http://" + IP + "/WebService50/chprivado/getCHPrivado.php?id_chpr=";
    public static final String URL_UPDATE_CHPrivado     = "http://" + IP + "/WebService50/chprivado/updateCHPrivado.php";
    public static final String URL_GET_CHPrivadoOnce    = "http://" + IP + "/WebService50/chprivado/getCHPrivadoOnce.php?id_chpr=";
    //Mensajes
    public static final String URL_ADD_Mensaje =      "http://" + IP + "/WebService50/mensajes/addMensajes.php";
    public static final String URL_GET_ALL_Mensaje =  "http://" + IP + "/WebService50/mensajes/getAllMensajes.php";
    public static final String URL_DELETE_Mensaje =   "http://" + IP + "/WebService50/mensajes/deleteMensajes.php?id_msg=";
    public static final String URL_GET_Mensaje =      "http://" + IP + "/WebService50/mensajes/getMensajes.php?id_msg=";
    public static final String URL_UPDATE_Mensaje =   "http://" + IP + "/WebService50/mensajes/updateMensajes.php";





    //Keys that will be used to send the request to php scripts
    public static final String KEY_USER_ID          = "id_usu";
    public static final String KEY_USER_USUARIO     = "usuario";
    public static final String KEY_USER_CUENTA      = "cuenta";
    public static final String KEY_USER_PRIVACIDAD  = "privacidad";
    public static final String KEY_USER_PASSWORD    = "password";
    public static final String KEY_USER_EMAIL       = "email";
    // Broadcast
    public static final String KEY_BROAD_ID     = "id_brod";
    public static final String KEY_BROAD_FECHA  = "fecha_brod";
    public static final String KEY_BROAD_TITULO = "titulo_brod";
    public static final String KEY_BROAD_CONT   = "cont_brod";
    public static final String KEY_BROAD_POSX   = "posx";
    public static final String KEY_BROAD_POSY   = "posy";
    // Chat Publico
    public static final String KEY_CHPU_ID      = "id_chpu";
    public static final String KEY_CHPU_FECHA   = "fecha_chpu";
    public static final String KEY_CHPU_CONT    = "cont_chpu";
    public static final String KEY_CHPU_IDUSUF  = "id_usuf";
    public static final String KEY_CHPU_LATX    = "latx_chpu";
    public static final String KEY_CHPU_LONY    = "lony_chpu";
    // Comentarios
    public static final String KEY_COM_ID       = "id_com";
    public static final String KEY_COM_FECHA    = "fecha_com";
    public static final String KEY_COM_CONT     = "cont_com";
    public static final String KEY_COM_IDCHPU   = "id_chpu";
    public static final String KEY_COM_IDUSUF   = "id_usuf";
    // Chat Privado
    public static final String KEY_CHPR_ID              = "id_chpr";
    public static final String KEY_CHPR_FORM_ID_USUF    = "form_id_usuf";
    public static final String KEY_CHPR_TO_ID_USUF      = "to_id_usuf";
    public static final String KEY_CHPR_TITULO_CHPR     = "titulo_chpr";
    // Mensajes
    public static final String KEY_MSG_ID         = "id_msg";
    public static final String KEY_MSG_FECHA      = "fecha_msg";
    public static final String KEY_MSG_CONT       = "cont_msg";
    public static final String KEY_MSG_IDCHPRF    = "id_chprf";
    public static final String KEY_MSG_IDUSUF     = "id_usuf";




    //JSON Tags
    public static final String TAG_JSON_ARRAY   = "result";
    public static final String TAG_ID           = "id_usu";
    public static final String TAG_USUARIO      = "usuario";
    public static final String TAG_CUENTA       = "cuenta";
    public static final String TAG_PRIVACIDAD   = "privacidad";
    public static final String TAG_PASSWORD     = "password";
    public static final String TAG_EMAIL        = "email";
    // Broadcast
    public static final String TAG_JSON_ARRAY_BROD  = "result";
    public static final String TAG_ID_BROD          = "id_brod";
    public static final String TAG_FECHA_BROD       = "fecha_brod";
    public static final String TAG_TITULO_BROD      = "titulo_brod";
    public static final String TAG_CONT_BROD        = "cont_brod";
    public static final String TAG_POSX_BROD        = "posx";
    public static final String TAG_POSY_BROD        = "posy";
    // Chat Publico
    public static final String TAG_JSON_ARRAY_CHPU  = "result";
    public static final String TAG_ID_CHPU          = "id_chpu";
    public static final String TAG_FECHA_CHPU       = "fecha_chpu";
    public static final String TAG_CONT_CHPU        = "cont_chpu";
    public static final String TAG_IDUSUSF_CHPU     = "usuario";
    public static final String KEY_LATX_CHPU        = "latx_chpu";
    public static final String KEY_LONY_CHPU        = "lony_chpu";
    //Comentarios
    public static final String TAG_JSON_ARRAY_COM   = "result";
    public static final String TAG_ID_COM           = "id_com";
    public static final String TAG_FECHA_COM        = "fecha_com";
    public static final String TAG_CONT_COM         = "cont_com";
    public static final String TAG_IDCHPU_COM       = "id_chpu";
    public static final String TAG_IDUSUF_COM       = "id_usuf";
    //Chat Privado
    public static final String TAG_JSON_ARRAY_CHPR  = "result";
    public static final String TAG_ID_CHPR          = "id_chpr";
    public static final String TAG_FORM_ID_USUF     = "form_id_usuf";
    public static final String TAG_TO_ID_USUF       = "to_id_usuf";
    public static final String TAG_TITULO_CHPR      = "titulo_chpr";
    public static final String TAG_DE               = "DE";
    public static final String TAG_PARA             = "PARA";
    // Mensajes
    public static final String TAG_ID_MSG       = "id_msg";
    public static final String TAG_FECHA_MSG    = "fecha_msg";
    public static final String TAG_CONT_MSG     = "cont_msg";
    public static final String TAG_IDCHPRF_MSG  = "id_chpr";
    public static final String TAG_IDUSUF_MSG   = "id_usuf";




    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    public static final String REGISTER_SUCCESS = "User Added Successfully";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";
    public static final String IDUSER_SHARED_PREF = "id_usu";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";


    //employee id to pass with intent
    public static final String USER_ID = "user_id";
    public static final String CHPU_ID = "id_chpu";
    public static final String CHPR_ID = "id_chpr";

    public static String ID_USUARIOS = "";
    public static String ID_CHPublico = "";
    public static String ID_CHPrivado = "";

}
