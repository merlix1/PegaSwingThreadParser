# PegaSwingThreadParser

Note 1: This project is using Swing and JavaFX (well, mostly JavaFX but some weird performance issue made me use Swing for some views).

Note 2: No Spring api used here.

Note 3: If you import this project in Eclipse with Java 8, follow these steps afterwward:
Go to project properties->build path->library tab-> expand JRE system library-> You should see "no rules"->add following rule:
Accessible: javafx/**
