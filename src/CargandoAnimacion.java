


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CargandoAnimacion {

    private Timer timer;
    private int puntoCount = 0;

    public void iniciarAnimacion(JLabel label) {
        timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder texto = new StringBuilder("Powered by P&S");
                for (int i = 0; i < puntoCount; i++) {
                    texto.append(".");
                }
                label.setText(texto.toString());
                puntoCount++;
                if (puntoCount > 5) {
                    puntoCount = 0;
                }
            }
        });
        timer.start();
    }

    public void detenerAnimacion() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }
}
