package husvetilog;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    final static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String args[]) {
        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            n = 10;
        }
        final Level VIOLATION = Level.forName("VIOLATION", 150); // szint a karantén szabályok megsértésének naplózásához
        final Level REQUEST = Level.forName("REQUEST", 350); // szint kérelmek naplózásához
        for (int i = 1; i <= n; i++) {
            logger.log(VIOLATION, "Zöld erdőben jártam,");
            logger.info("Kék ibolyát láttam,");
            logger.warn("El akart hervadni,");
            logger.log(REQUEST, "Szabad-e locsolni?");
            try {
                Thread.sleep(10); // egy kis késleltetés
            } catch (InterruptedException e) {}
        }

    }
}
