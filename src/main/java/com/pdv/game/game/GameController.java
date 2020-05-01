package com.pdv.game.game;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class GameController {

    private Game game;

    @RequestMapping(path = "/game/{count}", method = RequestMethod.GET)
    public void setCount(@PathVariable String count) {
        game = new Game();
        game.setPlayerCount(Integer.parseInt(count));
        game.initGame();
    }

    @RequestMapping(path = "/game/username/{id}/{username}", method = RequestMethod.GET)
    public void getUsername(@PathVariable String id, @PathVariable String username) {
        game.addPlayer(Integer.parseInt(id), username);
    }

    @RequestMapping(path = "/game/progress/{id}/{number}", method = RequestMethod.GET)
    public ResponseModel guessNumber(@PathVariable String id, @PathVariable String number) {
        return game.enterNumber(Integer.parseInt(id), Integer.parseInt(number));
    }

    @RequestMapping(path = "/game/results/", method = RequestMethod.GET)
    public ResponseModel result() {
        return game.endGame();
    }

}
