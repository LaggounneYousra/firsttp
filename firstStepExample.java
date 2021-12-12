
package tp01.oo;
import java.io.File;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class firstStepExample extends Util {
    final static String DB4OFILENAME = System.getProperty("user.home")
            +"/formula1.db4o";
    public static void main(String[] args){
    new File(DB4OFILENAME).delete();
    ObjectContainer db=Db4o.openFile(DB4OFILENAME);
    try{
     storeFirstPilot(db);
     storeSecondPilot(db);
     retrieveAllPilots(db);
     retrievePilotByName(db);
     retrievePilotByExactPoints(db);
     updatePilot(db);
     deleteFirstPilotByName(db);
     deleteSecondPilotByName(db);
     }
    finally{
    db.close();
        }
    }
    public static void accessDb4o(){
    ObjectContainer db=Db4o.openFile(DB4OFILENAME);
    try{
    // do somthing with db4o
      }
    finally{
    db.close();
     }
    }
    public static void listResult(ObjectSet result){
    System.out.println(result.size());
    while(result.hasNext()){
    System.out.println(result.next());
     }
    }
    public static void storeFirstPilot(ObjectContainer db){
    pilot pilot1=new pilot("yousra",100);
    db.store(pilot1);
    System.out.println("stored"+pilot1);
    }
    public static void storeSecondPilot(ObjectContainer db){
    pilot pilot2=new pilot("djihane",99);
    db.store(pilot2);
    System.out.println("stored"+pilot2);
    }
    public static void retrieveAllPilotQBE(ObjectContainer db){
    pilot proto=new pilot(null,0);
    ObjectSet result=db.queryByExample(pilot.class);
    listResult(result);
    }
     public static void retrieveAllPilots(ObjectContainer db){
     ObjectSet result=db.queryByExample(pilot.class);
    listResult(result);
     }
    public static void retrievePilotByName(ObjectContainer db){
    pilot proto=new pilot("yousra",0);
    ObjectSet result=db.queryByExample(proto);
    listResult(result);
    }
    public static void retrievePilotByExactPoints(ObjectContainer db){
    pilot proto=new pilot(null,100);
    ObjectSet result=db.queryByExample(proto);
    listResult(result);
    }
    public static void updatePilot(ObjectContainer db){
        ObjectSet result=db.queryByExample(new pilot("yousra",0));
        pilot found=(pilot)result.next();
        found.addPoints(11);
        db.store(found);
        System.out.println("added 11 points for"+found);
        retrieveAllPilots(db);

    }
        public static void deleteFirstPilotByName(ObjectContainer db){
        ObjectSet result=db.queryByExample(new pilot("yousra",0));
        pilot found=(pilot)result.next();
        db.delete(found);
        System.out.println("deleted"+found);
        retrieveAllPilots(db);

        }
         public static void deleteSecondPilotByName(ObjectContainer db){
        ObjectSet result=db.queryByExample(new pilot("djihane",0));
        pilot found=(pilot)result.next();
        db.delete(found);
        System.out.println("deleted"+found);
        retrieveAllPilots(db);

        }
        

}                                                                        

