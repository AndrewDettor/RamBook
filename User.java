import java.util.*;

public class User
{
    //INSTANCE FIELDS
    private String name;
    private int age;
    private String hometown;
    private String[] schools;
    private ArrayList<User> friendsList;

    //CONSTRUCTOR
    //NOTE - it leaves the friendsList empty
    public User(String n, int a, String h, String[] s)
    {
        name = n;
        age = a;
        hometown = h;
        schools = s;
        friendsList = new ArrayList<User>(); 
    }//END Constructor

    // Accepts a List of Users that will replace the current friendsList
    public void bulkAddFriends(ArrayList<User> u)
    {
        friendsList = u;
    }//END bulkAddFriends

    public boolean equals(User other) 
    {
        //equals(User other) - this User and other are equal if name, age, and hometown match.

        if (name.equals(other.getName()) && age == other.getAge() && hometown.equals(other.getHometown())) 
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    public void addFriend(User other) 
    {
        //addFriend – adds a User to the friendsList
        friendsList.add(other);
    }

    public void unfriend(String friend) 
    {
        //unfriend - accepts a String parameter (representing a User’s name) 
        //and removes them from this User’s friendsList. The method returns no value.

        friend.toLowerCase();
        for (int i=0; i<friendsList.size(); i++) 
        {
            if ((friendsList.get(i).getName().toLowerCase()).equals(friend)) 
            {
                friendsList.remove(i);
            }   
        }
    } 

    public int countFriends() 
    {
        //countFriends – returns the count the total number of friends in the User’s friendsList.

        return friendsList.size();
    }

    public ArrayList<User> getMutualFriends(User other) 
    {
        //getMutualFriends(User other) - accepts another User as a parameter and returns a list of Users 
        //that represents the friends they have in common with this User. 
        //Make sure you use equals when checking if the friends are the same people.

        ArrayList<User> mutualList = new ArrayList<User>();
        for (User friend : friendsList) 
        {
            for (User friend2 : other.getFriendsList()) 
            {
                if (friend.equals(friend2)) 
                {
                    mutualList.add(friend);
                } 
            }
        }
        return mutualList;
    }

    public ArrayList<User> getHometownFriends() 
    {
        //getHometownFriends - returns all users in this User’s friendsList whose hometown is the same as this User’s hometown.

        ArrayList<User> hometownList = new ArrayList<User>();
        for (User i : this.getFriendsList()) 
        {
            if (this.hometown.equals(i.getHometown())) 
            {
                hometownList.add(i);
            }
        }
        return hometownList;
    }

    public ArrayList<User> getSchoolmates() 
    {
        //getSchoolmates – returns all users in this User’s friendsList that have gone at least one of the same schools

        ArrayList<User> mateList = new ArrayList<User>();
        boolean same;
        for (User x : this.getFriendsList()) 
        {
            same = false;
            for (int a=0; a<3; a++) 
            {
                for (int b=0; b<3; b++) 
                {
                    if (this.schools[a] != null && x.schools[b] != null)
                    {
                        if (this.schools[a].equals(x.schools[b])) 
                        {
                            same = true;
                        }
                    }
                }
            }
            if (same == true) 
            {
                mateList.add(x);
            }
        }
        return mateList;
    }

    public User suggestAFriend() 
    {
        //suggestAFriend - accepts no parameters and returns a User.
        //Not currently in your friendsList
        //Is a friend-of-a-friend
        //Has the same hometown as you

        ArrayList<User> NaF = new ArrayList<User>();
        ArrayList<User> NaFhometown = new ArrayList<User>();

        for (User friend : friendsList)
        {
            for (User fof : friend.getFriendsList())
            {
                if (!(this.equals(fof)))
                {
                    //check to make sure friend of friend is not a friend
                    if (!(fof.equals(friend)))
                    {
                        NaF.add(fof);
                        //fof is guaranteed to not be a friend, so check for same hometown
                        if (fof.getHometown().equals(this.hometown))
                        {
                            NaFhometown.add(fof);
                        }
                    }
                }
            }
        }

        if (NaFhometown.size() > 1)
        {
            return NaFhometown.get((int)(Math.random() * (NaFhometown.size()+1) ));
        } //if size == 1, then would print out values 0-1 NOT GOOD
        else if (NaFhometown.size() == 1) 
        {
            return NaFhometown.get(0);
        }
        else
        {
            if (NaF.size() > 1)
            {
                return NaF.get((int)(Math.random() * (NaFhometown.size()+1) ));
            }
            else if (NaF.size() == 1)
            {
                return NaF.get(0);
            }
            else
            {
                return null;
            }
        }
    }

    //GETS
    public String getName()
    {
        return name;
    }

    public int getAge() 
    {
        return age;
    }

    public String getHometown() 
    {
        return hometown;
    }

    public String[] getSchools() 
    {
        return schools;
    }

    public ArrayList<User> getFriendsList() 
    {
        return friendsList;
    }

    // Should print out all information for the user, nicely formatted
    public String toString()
    {
        String retStr = "";
        retStr += "Name: \t\t" + name + "\n";
        retStr += "Age: \t\t" + age + "\n";
        retStr += "Hometown: \t" + hometown + "\n";

        retStr += "Schools: \t";
        for (int i=0; i<=schools.length-1; i++)
        {
            if (schools[i] != null) 
            {
                retStr += (schools[i] + " ");
            }
        }
        retStr += ("\n");
        //retStr += (schools[schools.length-1] + "\n");

        retStr += "Friends: \t";
        for (int i=0; i<=friendsList.size()-1; i++)
        {
            retStr += ((friendsList.get(i)).getName() + " ");
        }
        retStr += ("\n");
        //retStr += (friendsList.get(friendsList.size()-1).getName()) + "\n";

        return retStr;
    }//END toString

}//END CLASS
