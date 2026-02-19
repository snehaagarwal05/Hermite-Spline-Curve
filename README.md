####### Hermite Spline Curve Drawing using Matrix Form #######
####### Computer Graphics Lab Assignment #######
Topic: Drawing Hermite Spline using 4 Mouse Clicks
Language Used: Java (Swing & AWT)

####### Objective #######
To implement and draw a Hermite Spline Curve using:
4 consecutive mouse clicks
Matrix form of Hermite basis
Start & end points
Start & end slope vectors

####### Problem Statement #######
Use mouse input to draw a Hermite spline curve where:
1st click → Start point (P₀)
2nd click → Defines starting slope
3rd click → Defines ending slope
4th click → End point (P₃)
The line segment between:
1st and 2nd point → Starting tangent
4th and 3rd point → Ending tangent
The curve must be computed using the matrix form of the Hermite spline equation.

####### Algorithm #######
Initialize empty list to store mouse clicks.
On each mouse click:
Store point coordinates.
When 4 points are collected:
Assign P₀, P₁, P₂, P₃.
Compute tangents:
T₀ = P₁ − P₀
T₁ = P₃ − P₂
For t = 0 to 1 (step 0.01):
Compute T vector = [t³ t² t 1]
Multiply T × H
Multiply result × G
Plot computed (x, y)
Draw line segments between computed points.
On 5th click → Reset and allow new curve.

####### Implementation Details #######
GUI built using Java Swing
Mouse input handled using MouseListener
Curve drawn using Graphics2D
Matrix multiplication implemented manually
Step size: 0.01 for smooth curve

####### Output Description #######
Red dots → Clicked control points
Gray lines → Tangent vectors
Blue curve → Hermite spline

####### How to Run #######
Compile:
javac HermiteSpline.java
Run:
java HermiteSpline
Click 4 points on the window to draw curve.
