package td_game.model.events;

public interface IGameEvent {
    void dispatch(IGameObserver observer);
}
