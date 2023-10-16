module com.cgvsu.rasterizationfxapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vsu.cs.cg.g8_1.kilchenko_v_p.lagrangepolynomial to javafx.fxml;
    exports ru.vsu.cs.cg.g8_1.kilchenko_v_p.lagrangepolynomial;
}