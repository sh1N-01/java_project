/*
 * Class: Person
 * -> This class represents a generic person with a name, serving as the superclass for more specific roles like Doctor or Patient.
 * * Exclusive Functions:
 * Person() - Constructor for initializing the person with a default name.
 * @param - none
 * @return - none
 * * Person(String) - Constructor for initializing the person with a specific name.
 * @param - String
 * @return - none
 * * setName() - Method for updating the person's name.
 * @param - String
 * @return - void
 * * getName() - Method for retrieving the person's name.
 * @param - none
 * @return - String
 * * toString() - Method for generating a string representation of the person's name.
 * @param - none
 * @return - String
 * * hasSameName() - Method for determining if another person object shares the same name (case-insensitive).
 * @param - Person
 * @return - boolean
 */
public class Person 
{
    private String name;

    public Person()
    {
        name = "No name yet";
    }

    public Person(String initialName)
    {
        this.name = initialName;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public String getName()
    {
        return this.name;
    }

    public String toString() 
    {
        return "Name: " + this.name;
    }

    public boolean hasSameName(Person otherPerson)
    {
        return this.name.equalsIgnoreCase(otherPerson.name);
    }
}
