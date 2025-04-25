package modelo;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class animaciones {

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
}
