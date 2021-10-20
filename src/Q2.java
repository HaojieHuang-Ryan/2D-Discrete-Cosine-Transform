import java.io.*;

public class Q2
{
    public static void main(String[] args) throws IOException
    {
        // Read file
        InputStream inFile = new FileInputStream("Q2.in");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inFile));
        // Output file
        OutputStream outFile = new FileOutputStream("Q2.out");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outFile));
        String read;
        while ((read = bufferedReader.readLine()) != null)
        {
            // Get original matrix
            int N = Integer.parseInt(read);
            int[][] original_matrix = new int[N][N];
            for (int i=0; i<N; i++)
            {
                read = bufferedReader.readLine();
                String[] token = read.split(" ");
                for (int j=0; j<N; j++)
                {
                    original_matrix[i][j] = Integer.parseInt(token[j]);
                }
            }
            // Get cosine transform matrix
            double[][] DCT = new double[N][N];
            double a;
            for (int i=0; i<N; i++)
            {
                for (int j=0; j<N; j++)
                {
                    if (i == 0)
                    {
                        a = Math.sqrt(1.0/N);
                    }
                    else
                    {
                        a = Math.sqrt(2.0/N);
                    }
                    DCT[i][j] = a * Math.cos(((2*j+1)*(i*Math.PI))/(2*N));
                }
            }
            // Get inverse matrix of cosine transform matrix
            double[][] DCT_T = new double[N][N];
            for (int i=0; i<N; i++)
            {
                for (int j=0; j<N; j++)
                {
                    DCT_T[j][i]= DCT[i][j];
                }
            }
            // Get final matrix
            double[][] temp_matrix = new double[N][N];
            double[][] final_matrix = new double[N][N];
            int[][] final_matrix_round = new int[N][N];
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    temp_matrix[i][j] = 0;
                    for (int k = 0; k < N; k++)
                    {
                        temp_matrix[i][j] += DCT[i][k] * original_matrix[k][j];
                    }
                }
            }
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    final_matrix[i][j] = 0;
                    for (int k = 0; k < N; k++)
                    {
                        final_matrix[i][j] += temp_matrix[i][k] * DCT_T[k][j];
                    }
                }
            }
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    final_matrix_round[i][j] = (int) Math.round(final_matrix[i][j]);
                }
            }
            // Write file
            for (int i=0; i<N; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    bufferedWriter.write(final_matrix_round[i][j] + " ");
                }
                bufferedWriter.write("\n");
            }
            bufferedWriter.write("\n");
        }
        // Close reader
        inFile.close();
        bufferedReader.close();
        // Close writer
        bufferedWriter.close();
        outFile.close();
    }
}