package com.pdv.game.game;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class GameController {

    private Game game;

    @RequestMapping(path = "/init", method = RequestMethod.GET)
    public ResponseModel initGame() {
        if(game == null || game.getStartFromBeginning()) {
            game = new Game();
        }
        return game.getResponseModel();
    }

    @RequestMapping(path = "/game/{count}", method = RequestMethod.GET)
    public void setCount(@PathVariable String count) {
        game.setPlayerCount(Integer.parseInt(count));
        game.initGame();
    }

    @RequestMapping(path = "/game/status", method = RequestMethod.GET)
    public ResponseModel getStatus() {
        return game.getResponseModel();
    }

    @RequestMapping(path = "/game/username/{username}", method = RequestMethod.GET)
    public ResponseModel getUsername(@PathVariable String username) {
        return game.addPlayer(username);
    }

    @RequestMapping(path = "/game/progress/{id}/{number}", method = RequestMethod.GET)
    public ResponseModel guessNumber(@PathVariable String id, @PathVariable String number) {
        return game.enterNumber(Integer.parseInt(id), Integer.parseInt(number));
    }

    @RequestMapping(path = "/game/results", method = RequestMethod.GET)
    public ResponseModel result() {
        return game.endGame();
    }

}
