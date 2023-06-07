import java.util.*;

public class SecurityCheck {
    Line headLine;
    Line tailLine;
    Line cursorLine;
    private int lineCount;
    private int[]lines;


    public SecurityCheck(){
        headLine = new Line();
        tailLine = headLine;
        cursorLine = headLine;
        lineCount = 1;
    }

    public int getLineCount() {
        return lineCount;
    }




    public void addPerson(String name, int seatNumber)throws Exception{
        if (isSeatTaken(seatNumber)) {
            throw new TakenSeatException("seat is taken");
        }

        // Find the shortest line
        Line shortestLine = getShortestLine();

        // Add the person to the shortest line
        shortestLine.addPerson(new Person(name, seatNumber));

        // Rebalance the lines
        //rebalancedLines();
    }
    public boolean isSeatTaken(int seatNumber){
        Line currLine = headLine;
        while (currLine != null) {
            Person currentPerson = currLine.getHeadPerson();
            while (currentPerson != null) {
                if (currentPerson.getSeatNumber() == seatNumber) {
                    return true;
                }
                currentPerson = currentPerson.getNextPerson();
            }
            currLine = currLine.getLineLink();
        }
        return false;
    }
    private Line getShortestLine() {
        // Set the cursor to the first line
        cursorLine = headLine;

        // Initialize the shortest line to the first line
        Line shortestLine = cursorLine;

        // Iterate over all the lines
        while (cursorLine != null) {
            // If the current line is shorter than the shortest line, update the shortest line
            if (cursorLine.getLength() < shortestLine.getLength()) {
                shortestLine = cursorLine;
            }

            // Move the cursor to the next line
            cursorLine = cursorLine.getLineLink();
        }

        return shortestLine;
    }
    public int size() {
        int count = 0;
        Line currentLine = headLine;
        while (currentLine != null) {
            count += currentLine.size();
            currentLine = currentLine.getLineLink();
        }
        return count;
    }



    public boolean isBalanced() {
        Line currLine = headLine;
        int maxLen = currLine.getLength();
        int minLen = currLine.getLength();

        while (currLine != null) {
            int length = currLine.getLength();

            if (length > maxLen) {
                maxLen = length;
            }

            if (length < minLen) {
                minLen = length;
            }

            currLine = currLine.getLineLink();
        }

        return (maxLen - minLen) <= 1;
    }
    private void rebalancedLines() {

        // find the line with the max length
        while (isBalanced()==false){
            int maxLength = Integer.MIN_VALUE;
            int minLength = Integer.MAX_VALUE;
            cursorLine = headLine;
            Line maxLine = cursorLine;

            while(cursorLine != null) {
                if(maxLength < cursorLine.length) {
                    maxLength = cursorLine.length;
                    maxLine = cursorLine;
                }
                cursorLine = cursorLine.lineLink;
            }
            // find the line with the min length
            cursorLine = headLine;
            Line minLine = cursorLine;
            while(cursorLine != null) {
                if(minLength > cursorLine.length) {
                    minLength = cursorLine.length;
                    minLine = cursorLine;
                }
                cursorLine = cursorLine.lineLink;
            }

            if(Math.abs(maxLength - minLength) <= 1) {
                return;
            }else {
                Person removedPerson = maxLine.removeFrontPerson();
                removedPerson.nextPerson = null;
                minLine.addPerson(removedPerson);
                //shuffleLines(); //recursion
            }
        }

    }

    public void addNewLines(int newLines) throws InvalidLineCountException {
        if(newLines < 0) {
            throw new InvalidLineCountException("invalid count");
        }
        int temp;
        temp = newLines;
        while(newLines >0) {
            Line l = new Line();
            tailLine.lineLink = l;
            tailLine = l;
            newLines--;

        }
        lineCount+=temp;
        rebalancedLines();
    }

    private void removeLine(Line l) {
        if(headLine.equals(l)) {
            while(l.length > 0) {
                tailLine.addPerson(l.removeFrontPerson());
            }
        }else {
            while (l.length > 0) {
                headLine.addPerson(l.removeFrontPerson());
            }
        }

        l.lineLink = null;
    }


    public void removeLines(int[] removedLines) throws SingleLineRemovalException, LineDoesNotExistException {
        Arrays.sort(removedLines);

        if (lineCount == 1) {
            throw new SingleLineRemovalException("There is only one line.");
        }
        if(lineCount ==0){
            throw new LineDoesNotExistException("line does not exist");
        }

        int index = 1;
        int p = 0;
        cursorLine = headLine;
        Line prev = null;

        while (cursorLine != null) {
            if (index == removedLines[p]) {
                if (cursorLine.equals(headLine)) {
                    headLine = cursorLine.lineLink;
                    prev = null;
                } else {
                    prev.lineLink = cursorLine.lineLink;
                }
                if (cursorLine.equals(tailLine)) {
                    tailLine = prev;
                }
                removeLine(cursorLine);
                p++;
            } else {
                prev = cursorLine;
            }
            cursorLine = cursorLine.lineLink;
            index++;
        }


        lineCount -= removedLines.length;
        rebalancedLines();
    }


    public Person removeNextAttendee() throws AllLinesEmptyException{
        // find line with largest length
        cursorLine = headLine;
        int maxLength = Integer.MIN_VALUE;
        while(cursorLine != null) {
            if(maxLength < cursorLine.length) {
                maxLength = cursorLine.length;
            }
            cursorLine = cursorLine.lineLink;
        }

        if(maxLength == 0) {
            throw new AllLinesEmptyException("All line empty.");
        }

        //find the smallest seat number in all lines with length maxLength
        cursorLine = headLine;
        int smallestSeatNumber = Integer.MAX_VALUE;
        Person smallestPerson = null;
        while(cursorLine != null) {
            if(cursorLine.length == maxLength) {
                Person currentPerson = cursorLine.getHeadPerson();
                while (currentPerson != null) {
                    if(currentPerson.getSeatNumber() < smallestSeatNumber) {
                        smallestSeatNumber = currentPerson.getSeatNumber();
                        smallestPerson = currentPerson;
                    }
                    currentPerson = currentPerson.getNextPerson();
                }
            }
            cursorLine = cursorLine.lineLink;
        }

        if (smallestPerson == null) {
            throw new AllLinesEmptyException("All line empty.");
        }

        return smallestPerson;


    }
    public void printAllLines() {
        System.out.println( "Line  | Name                 | Seat Number |");
        System.out.println("==============================================");
        Line currentLine = headLine;
        while (currentLine != null) {
            Person currentPerson = currentLine.getHeadPerson();

            while (currentPerson != null) {
                System.out.printf("| %-4d| %-20s| %-12d|\n", lineCount-currentLine.getLineIndex()+1, currentPerson.getName(), currentPerson.getSeatNumber());
                currentPerson = currentPerson.getNextPerson();
            }
            currentLine = currentLine.getLineLink();
        }
        System.out.println("\nNumber of people in each line:");
        currentLine = headLine;
        while (currentLine != null) {
            System.out.printf("Line %d: %d Person Waiting\n", currentLine.getLineIndex(), currentLine.getLength());
            currentLine = currentLine.getLineLink();
        }
    }
    public void printLines(){
        Line currentLine = headLine;
        System.out.println("\nNumber of people in each line:");
        currentLine = headLine;
        while (currentLine != null) {
            System.out.printf("Line %d: %d Person Waiting\n", currentLine.getLineIndex(), currentLine.getLength());
            currentLine = currentLine.getLineLink();
        }
    }


}

