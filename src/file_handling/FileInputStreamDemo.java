package file_handling;
/**
 * here in this program we are reading a file using InputStream object
 * InputStream object throws FileNotFoundException if the file is not found,
 * while creating the object we specify the path of our file
 *
 * then we read the contents of the file using the read method
 * which reads a byte value from the file and returns a byte in the form of an
 * integer then we type cast it to the char . Also read method throws IOException
 * so either we have to use throws clause or use try and catch block
 *
 * After the reading is done close the file to release the resources from the file
 *
 * For writing the file we create object of FileOutputStream. It throws  FileNotFoundException
 * if the file cannot be opened for writing
 *
 * then to write the value into the file we use the method write() which throws IOException
 * with passing argument as bytevalue. Only the low order 8 bits are written in the file.
 *
 * And close the file if you are finished.

 */

import java.io.*;

public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {
        String currentDir = System.getProperty("user.dir");
        try (FileOutputStream fout = new FileOutputStream(currentDir + File.separator + "hello1.txt");
             FileInputStream fin = new FileInputStream(currentDir + File.separator + "hello.txt")) {

            int k;
            while ((k = fin.read()) != -1) {
                // while writing a value if pass very large number as argument then only the lower 8 bit values are written
                //also then the encoding also changes.
                fout.write(k);
                System.out.print((char) k);
            }
        }
    }
}
