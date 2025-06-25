import entities.Answer;
import entities.Question;
import entities.User;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Stackoverflow stackoverflow = new Stackoverflow();

        // Services
        User raghu = new User("raghu");
        List<User> participants = new ArrayList<>();
        for(int i=0; i<4; i++) {
            User participant = new User("randomBoi" + i);
            participants.add(participant);
        }

        Question q1 = stackoverflow.askQuestion(raghu, "TU BANEGA ROADIE?", "KAISE BANEGA ROADIEE, BTA KYA AATA HAI",
                List.of("Roadies"));
        Answer a1 = stackoverflow.postAnswer(participants.get(0), q1, "CHAATI PAR CYLINDER MAAR LETA HU!!");
        Answer a2 = stackoverflow.postAnswer(participants.get(1), q1, "DO BIKES PAR EK SATH ENTRY MAARTA HU");
        Answer a3 = stackoverflow.postAnswer(participants.get(2), q1, "Mimicry karleta hu hehe");
        Answer a4 = stackoverflow.postAnswer(participants.get(3), q1, "Usme kya rakha ha");

        stackoverflow.upvote(participants.get(0), q1);
        stackoverflow.markAsAccepted(raghu, q1, a2);
        stackoverflow.upvote(participants.get(3), a1);
        stackoverflow.upvote(participants.get(1), a2);

    }
}