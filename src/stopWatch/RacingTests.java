package stopWatch;

import org.junit.jupiter.api.Test;

public class RacingTests {
    @Test
    void shouldKnowTheElapsedTime(){
        givenANewRaceWithParticipants();
        WhenTheRaceIsStarted();
        AndTimeHasElapsed(0);
        ThenTheRaceIsNotFinished();
        AndItsDurationIs(0);
    }

    private void AndItsDurationIs(int i) {

    }

    private void ThenTheRaceIsNotFinished() {

    }

    private void AndTimeHasElapsed(int i) {

    }

    private void WhenTheRaceIsStarted() {

    }

    private void givenANewRaceWithParticipants() {

    }
}
