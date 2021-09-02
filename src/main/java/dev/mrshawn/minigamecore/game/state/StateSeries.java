package dev.mrshawn.minigamecore.game.state;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class StateSeries {

	private final List<State> states = new ArrayList<>();
	// Current state index
	private int current = 0;
	// Prevent the states from changing
	private boolean frozen = false;

	// Skip the current state
	private boolean skipping = false;

	public StateSeries(State state) {
		addNext(state);
	}

	public StateSeries addNext(State state) {
		states.add(state);
		return this;
	}

	public StateSeries addNext(List<State> states) {
		this.states.addAll(states);
		return this;
	}

	public void start() {
		if (states.isEmpty()) {
			end();
			return;
		}

		states.get(current).start();
	}

	public void update() {
		// Update the current state
		states.get(current).update();

		// Check if it's time to end the current state
		if ((states.get(current).isReadyToEnd() && !frozen) || skipping) {
			if (skipping)
				skipping = false;

			// End the current state
			states.get(current).end();
			current++;

			// No more states left
			if (current >= states.size()) {
				end();
				return;
			}

			// Start the next state
			states.get(current).start();
		}
	}

	public void end() {
		if (current < states.size()) {
			states.get(current).end();
		}
	}

	public void skip() {
		skipping = true;
	}

	public void freeze() {
		frozen = true;
	}

	public boolean isReadyToEnd() {
		return current == states.size() - 1 && states.get(current).isReadyToEnd();
	}

}
