/**

 A class representing a line of people waiting for a concert.

 A line is composed of a linked list of Person objects,

 which are sorted in ascending order according to their seat number.

 The class provides methods to add and remove people from the line,

 as well as to check the size and the presence of a person in the line.
 */
public class Line {
    Person headPerson;
    Person tailPerson;
    int length;
    Line lineLink;

    /**

     Sets the head Person of the line.
     @param headPerson The head Person of the line
     */
    public void setHeadPerson(Person headPerson) {
        this.headPerson = headPerson;
    }
    /**

     Returns the head Person of the line.
     @return The head Person of the line
     */
    public Person getHeadPerson() {
        return headPerson;
    }
    /**

     Sets the tail Person of the line.
     @param tailPerson The tail Person of the line
     */

    public void setTailPerson(Person tailPerson) {
        this.tailPerson = tailPerson;
    }
    /**

     Returns the tail Person of the line.
     @return The tail Person of the line
     */
    public Person getTailPerson() {
        return tailPerson;
    }
    /**

     Sets the length of the line.
     @param length The length of the line
     */
    public void setLength(int length) {
        this.length = length;
    }
    /**

     Returns the length of the line.
     @return The length of the line
     */
    public int getLength() {
        return length;
    }
    /**

     Sets the link to the next line.
     @param lineLink The link to the next line
     */
    public void setLineLink(Line lineLink) {
        this.lineLink = lineLink;
    }
    /**

     Returns the link to the next line.
     @return The link to the next line
     */
    public Line getLineLink() {
        return lineLink;
    }
    /**

     Constructs an empty Line object.
     */
    public Line(){
        //headPerson = null;
        //tailPerson = null;
        this.length = 0;
        lineLink = null;
    }
    /**

     Adds a person to the line, while keeping the line sorted by seat number.
     @param attendee The person to add to the line
     */
    public void addPerson(Person attendee){
        if(length ==0){
            headPerson = attendee;
            tailPerson = attendee;
        } else if (attendee.getSeatNumber()< headPerson.getSeatNumber()){
            attendee.setNextPerson(headPerson);
            headPerson = attendee;
        } else{
            Person curr = headPerson;
            Person prev = null;
            while(curr!=null&&attendee.getSeatNumber()> curr.getSeatNumber()){
                prev = curr;
                curr = curr.getNextPerson();
            }
            attendee.setNextPerson(curr);
            prev.setNextPerson(attendee);
            if(curr == null){
                tailPerson = attendee;
            }
        }
        length++;
    }
    /**

     Removes the front Person of the line, and returns it.
     @return The front Person of the line
     */
    public Person removeFrontPerson(){
        Person temp = headPerson;
        if(headPerson!=null) {
            //System.out.println("removed in line: " + headPerson.getName());
            headPerson = headPerson.getNextPerson();

            length--;
            if (headPerson == null) {
                tailPerson = null;
            }
        }
        return temp;

    }

    /**
     * get the index of line for a person
     * @return the index of the line
     */
    public int getLineIndex() {
        Line currentLine = this;
        int index = 1;
        while (currentLine.lineLink != null) {
            index++;
            currentLine = currentLine.lineLink;
        }
        return index;
    }
    public int size() {
        int count = 0;
        Person currentPerson = headPerson;
        while (currentPerson != null) {
            count++;
            currentPerson = currentPerson.getNextPerson();
        }
        return count;
    }
    public boolean containsPerson(Person person) {
        Person currentPerson = headPerson;
        while (currentPerson != null) {
            if (currentPerson == person) {
                return true;
            }
            currentPerson = currentPerson.getNextPerson();
        }
        return false;
    }

}
