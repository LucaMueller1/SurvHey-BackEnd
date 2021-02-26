package DAO;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Entity
@Table(name = "SurvHey_DB.Survey_Participant")
public class ParticipantDAO implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "Participant_ID")
    private long Participant_ID;

    @Column(name = "IP_Adress")
    private String IP_Adress;



    //Constructors
    public ParticipantDAO(long participant_ID, String IP_Adress) {
        Participant_ID = participant_ID;
        this.IP_Adress = IP_Adress;
    }

    public ParticipantDAO() {
        Participant_ID = -1;
        this.IP_Adress = null;
    }



    //getter & setter


    public long getParticipant_ID() {
        return Participant_ID;
    }

    public void setParticipant_ID(long participant_ID) {
        Participant_ID = participant_ID;
    }

    public String getIP_Adress() {
        return IP_Adress;
    }

    public void setIP_Adress(String IP_Adress) {
        this.IP_Adress = IP_Adress;
    }


    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }
}
