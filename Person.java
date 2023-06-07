/**
 * A class representing a person in singly linked list
 */
public class Person {
    /**

     Constructs a Person object with no parameters.
     */
    public Person(){

    }
    /**

     Constructs a Person object with a name and seat number.
     @param name the name of the person.
     @param seatNumber the seat number of the person.
     */
    public Person(String name,int seatNumber){
        this.name = name;
        this.seatNumber = seatNumber;
    }

    String name;
    int seatNumber;
    Person nextPerson;
    /**

     Sets the name of the person.
     @param name the name of the person.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**

     Returns the name of the person.
     @return the name of the person.
     */
    public String getName() {
        return name;
    }
    /**

     Sets the seat number of the person.
     @param seatNumber the seat number of the person.
     */
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    /**

     Returns the seat number of the person.
     @return the seat number of the person.
     */
    public int getSeatNumber() {
        return seatNumber;
    }
    /**

     Sets the reference to the next person in the queue.
     @param nextPerson the reference to the next person in the queue.
     */
    public void setNextPerson(Person nextPerson) {
        this.nextPerson = nextPerson;
    }
    /**

     Returns the reference to the next person in the queue.
     @return the reference to the next person in the queue.
     */
    public Person getNextPerson() {
        return nextPerson;
    }



}
