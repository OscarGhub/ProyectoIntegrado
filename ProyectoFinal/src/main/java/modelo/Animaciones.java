package modelo;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animaciones {

    /**
     * Método para animar la imagen del usuario.
     * @param imagen La imagen que se va a animar.
     */

    public static void animarImagenUsuario(ImageView imagen) {
        FadeTransition fade = new FadeTransition(Duration.seconds(1.5), imagen);
        fade.setFromValue(1.0);
        fade.setToValue(0.1);
        fade.setAutoReverse(true);
        fade.setCycleCount(FadeTransition.INDEFINITE);
        fade.play();
    }

    public static void animarAgrandar(ImageView imagen) {
        ScaleTransition escalar = new ScaleTransition(Duration.seconds(1.5), imagen);
        escalar.setToX(1.3);
        escalar.setToY(1.3);
        escalar.setAutoReverse(true);
        escalar.setCycleCount(ScaleTransition.INDEFINITE);
        escalar.play();
    }

    public static void animarRotacion(ImageView imagen) {
        RotateTransition rotar = new RotateTransition(Duration.seconds(6), imagen);
        rotar.setByAngle(360);
        rotar.setCycleCount(RotateTransition.INDEFINITE);
        rotar.setInterpolator(javafx.animation.Interpolator.LINEAR);
        rotar.play();
    }


}
