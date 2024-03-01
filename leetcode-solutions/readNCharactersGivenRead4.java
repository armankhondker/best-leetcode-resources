Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.

 

Method read4:

The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.

The return value is the number of actual characters read.

Note that read4() has its own file pointer, much like FILE *fp in C.




public int read(char[] buf, int n) {

        char[] temp = new char[4];  //Store our read chars from Read4
        int total = 0;
        
        while(total < n){ //THIS CAN BE WHILE TRUE, because not actually terminting condition 
            
            /*Read and store characters in Temp. Count will store total chars read from Read4*/
            int count = read4(temp);
        
            /*Even if we read 4 chars from Read4, 
            we don't want to exceed N and only want to read chars till N.*/
            count = Math.min(count, n-total);
            
            //Transfer all the characters read from Read4 to our buffer
            for(int i = 0;  i < count; i++){
                buf[total] = temp[i];
                total++;
            }
            
            //done. We can't read more characters. WE HAVE REACHED END OF FILE!! THIS IS THE REAL
            //TERMINATING CONDITION!!!
            if(count < 4) break;
        }
        
        return total;
    }