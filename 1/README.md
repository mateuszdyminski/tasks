### Task 1

Write Run-length encoding algorithm 

##### Description

RLE is a very simple form of lossless data compression in which runs of data. 

RLE transforms input string into series of tuples where each tuple contains single data value and count, rather than as the original run.
 
Example:

`EEEOOOP` is transformed into `3E3O1P`


#### Sample test data

| Input                                                               | Expected output    |
| ------------------------------------------------------------------- | ------------------ |
|                                                                     |                    |
| W                                                                   | 1W                 |
| WWWWHHHH                                                            | 4W4H               |
| WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW | 12W1B12W3B24W1B14W |

#### Sample solution in Java

```java 
public static String encode(String source) {
    StringBuffer dest = new StringBuffer();
    for (int i = 0; i < source.length(); i++) {
        int runLength = 1;
        while (i + 1 < source.length()
                && source.charAt(i) == source.charAt(i + 1)) {
            runLength++;
            i++;
        }
        dest.append(runLength);
        dest.append(source.charAt(i));
    }
    return dest.toString();
}
```
