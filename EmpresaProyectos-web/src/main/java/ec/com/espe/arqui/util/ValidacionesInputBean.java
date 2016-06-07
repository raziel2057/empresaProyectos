/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.espe.arqui.util;

/**
 *
 * @author ale
 */
public class ValidacionesInputBean {

    public static final int LONGITUD_CORREO = 30;

    public ValidacionesInputBean() {
    }

    public String validateEmail(String correo) {
        // Reqular expression pattern to validate the format submitted

        String validator = "^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+"
                + "(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,})$";

        String retorno;

        if (correo.matches(validator)) {
            if (correo.length() <= LONGITUD_CORREO) {
                retorno = "se"; //Sin error.
            } else {
                retorno = "La longitud del correo excede el limite de " + LONGITUD_CORREO;
            }
        } else {
            retorno = "Correo no valido";
        }

        return retorno;
    }

    public String validateTextoSoloLetras(String texto, int longitudMaxima) { //Incluye letras en mayúscula, minúcula, espacios
        // Reqular expression pattern to validate the format submitted

        //String validator = "^\\p{L}+(?: \\p{L}+)*$";
        String validator = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ_\\s-]*$";

        String retorno;
        if (texto.matches(validator)) {
            if (texto.length() <= longitudMaxima) {
                retorno = "se"; //Sin error.
            } else {
                retorno = "La longitud del texto excede el límite de " + longitudMaxima;
            }
        } else {
            retorno = "Texto no válido. Texto debe contener solo letras";
        }

        return retorno;
    }

    public String validateTextoLetrasNumerosCaracteresEspeciales(String texto, int longitudMaxima) {
        // Reqular expression pattern to validate the format submitted

        String validator = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ.,;#_\\s-]*$";

        String retorno;
        if (texto.matches(validator)) {
            if (texto.length() <= longitudMaxima) {
                retorno = "se"; //Sin error.
            } else {
                retorno = "La longitud del texto excede el límite de " + longitudMaxima;
            }
        } else {
            retorno = "Texto no válido. Texto debe incluir solo letras, números y caracteres especiales.";
        }

        return retorno;
    }

    public String validateTextoLetrasNumeros(String texto, int longitudMaxima) {
        // Reqular expression pattern to validate the format submitted

        String validator = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ_\\s-]*$";

        String retorno;
        if (texto.matches(validator)) {
            if (texto.length() <= longitudMaxima) {
                retorno = "se"; //Sin error.
            } else {
                retorno = "La longitud del texto excede el límite de " + longitudMaxima;
            }
        } else {
            retorno = "Texto no válido. Texto debe incluir solo letras y números";
        }

        return retorno;
    }

    public String validateFecha(String fecha) {
        // Reqular expression pattern to validate the format submitted

        String validator = "\\d{4}-\\d{2}-\\d{2}"; //formato "YYYY-MM-DD"

        String retorno;
        if (fecha.matches(validator)) {
            retorno = "se"; //Sin error.
        } else {
            retorno = "Texto no válido";
        }

        return retorno;
    }

    public String validateNumeroEntero(String numero, int longitudMaxima) {
        // Reqular expression pattern to validate the format submitted

        String validator = "\\d+";

        String retorno;
        if (numero.matches(validator)) {
            if (numero.length() <= longitudMaxima) {
                retorno = "se"; //Sin error.
            } else {
                retorno = "La longitud del número excede el límite de " + longitudMaxima;
            }
        } else {
            retorno = "Número no válido";
        }

        return retorno;
    }

    public String validateNumeroDecimal(String numero, int longitudMaxima) { // La longitud: Ejemplo 10. 7 para enteros, 3 decimales
        // Reqular expression pattern to validate the format submitted

        String validator = "[0-9]+(\\.[0-9][0-9]?)?";
        longitudMaxima = longitudMaxima + 1;

        String retorno;
        if (numero.matches(validator)) {
            if (numero.length() <= longitudMaxima) {
                retorno = "se"; //Sin error.
            } else {
                retorno = "La longitud del número excede el límite de " + longitudMaxima;
            }
        } else {
            retorno = "Número no valido";
        }

        return retorno;
    }

    public boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
// Coeficientes de validación cédula
// El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepción ocurrio en el proceso de validación");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;
    }

}
