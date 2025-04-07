import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        //System.out.println("prevPt es el last point= " + prevPt);
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            //System.out.println("currPt debería ser el 1ero = " + currPt);
            double currDist = prevPt.distance(currPt);
            //System.out.println("distance de prevPT a currPt = " + currDist);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            //System.out.println(" totalPerim = totalPerim + currDist = " + currDist);
            // Update prevPt to be currPt
            prevPt = currPt;
            //System.out.println(" valor luego del 1er iteracion = " + prevPt);
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numberOfPoints = 0;
        for (Point p : s.getPoints()) {
            //System.out.println(p);
            numberOfPoints++;
        }
        //System.out.println("La figura tiene " + numberOfPoints + " puntos.");// Put code here
        return numberOfPoints;
    }

    public double getAverageLength(Shape s) {
        double averageLength = getPerimeter(s)/getNumPoints(s);// Put code here
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        double max = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > max) {
                max = currDist;
            }
            prevPt = currPt;
        }
        return max;
    }

    public double getLargestX(Shape s) {
        double max = 0;
        for (Point currPt : s.getPoints()) {
            double x = currPt.getX();
            double y = currPt.getY();
            
            if (x > max ) {
                max = x;
            } else if (y > max)
                max = y;
        }
        return max;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestP = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largestP) {
                largestP = length;
            }
        }
        return largestP;
    }

    public String getFileWithLargestPerimeter() {
        double largestP = 0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largestP) {
                largestP = length;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int amount = getNumPoints(s);
        double average = getAverageLength(s);
        double max = getLargestSide(s);
        double maxPoint = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("numPoints = " + amount);
        System.out.println("averageLength = " + average);
        System.out.println("largestSide = " + max);
        System.out.println("largestX = " + maxPoint);
        
        
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPMF = getLargestPerimeterMultipleFiles();// Put
        System.out.println("largestPMF = " + largestPMF);
    }

    public void testFileWithLargestPerimeter() {
        String fileLargestPMF = getFileWithLargestPerimeter();
        System.out.println("fileLargestPMF = " + fileLargestPMF);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
