package com.ygo.ygodeckbuilder.controller;

import com.ygo.ygodeckbuilder.domain.Card;
import com.ygo.ygodeckbuilder.service.CardService;
import com.ygo.ygodeckbuilder.service.FileExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

@Controller
public class CardController {
    private final CardService cardService;

    @Autowired
    private FileExporter fileExporter;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String firstPage(){
        return "redirect:/onlycards/1";
    }

    @GetMapping("/randomCard")
    public String showRandomCard(Model model){
        model.addAttribute("randomCard", cardService.getRandomCard());
        return "randomcard";
    }

    @GetMapping("/cards")
    public String showAllCards(){
        return "redirect:/cards/page/1";
    }

    @RequestMapping("/cards/getOne")
    @ResponseBody
    public Card getCard(Integer id){
        return cardService.getCardByCardId(id);
    }

    @GetMapping("/cards/page/{pageNr}")
    public String findPaginated(@PathVariable(value = "pageNr") int pageNr,
                                @RequestParam(name = "sortField",required = false,defaultValue = "cardName") String sortField,
                                @RequestParam(name = "sortDir",required = false,defaultValue = "asc") String sortDir,
                                @RequestParam(name="searchFilter",required = false,defaultValue = "") String searchFilter,
                                Model model){
        int pageSize = 12;
        Page<Card> page = cardService.findPaginated(pageNr,pageSize,sortField,sortDir,searchFilter);
        List<Card> cardList = page.getContent();

        model.addAttribute("currentPage", pageNr);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("searchFilter", searchFilter);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("currentDeck", cardService.getCurrentDeck().getCardList());
        model.addAttribute("currentDeckName", cardService.getCurrentDeck().getDeckName());

        model.addAttribute("cards", cardList);
        return "cards";
    }

    @GetMapping("cards/addToDeck")
    public String addCardToDeck(@RequestParam(name = "id") int id,
                                @RequestParam(value = "pageNr",required = false,defaultValue = "1") int pageNr,
                                @RequestParam(name = "sortField",required = false,defaultValue = "cardName") String sortField,
                                @RequestParam(name = "sortDir",required = false,defaultValue = "asc") String sortDir,
                                @RequestParam(name="searchFilter",required = false,defaultValue = "") String searchFilter){
        cardService.addCardToDeck(id);
        String url = "redirect:/cards/page/" + pageNr + "?sortField=" + sortField + "&sortDir" + sortDir + "&searchFilter="+ searchFilter;
        return url;
    }

    @GetMapping("cards/deleteFromDeck")
    public String deleteFromDeck(@RequestParam(name = "id") int id,
                                 @RequestParam(value = "pageNr",required = false,defaultValue = "1") int pageNr,
                                 @RequestParam(name = "sortField",required = false,defaultValue = "cardName") String sortField,
                                 @RequestParam(name = "sortDir",required = false,defaultValue = "asc") String sortDir,
                                 @RequestParam(name="searchFilter",required = false,defaultValue = "") String searchFilter){
        cardService.deleteCardFromDeck(id);
        String url = "redirect:/cards/page/" + pageNr + "?sortField=" + sortField + "&sortDir" + sortDir + "&searchFilter="+ searchFilter;
        return url;
    }

    @GetMapping("cards/clearDeck")
    public String clearDeck(){
        cardService.clearDeck();
        return "redirect:/cards/page/1";
    }

    @RequestMapping("cards/export")
    public ResponseEntity<InputStreamResource> downloadTextFileExample1() throws FileNotFoundException {
        String fileName = "deck.ydk";
        String fileContent = cardService.getDeckAsYdkFile();

        // Create text file
        Path exportedPath = fileExporter.export(fileContent, fileName);

        // Download file with InputStreamResource
        File exportedFile = exportedPath.toFile();
        FileInputStream fileInputStream = new FileInputStream(exportedFile);
        InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(exportedFile.length())
                .body(inputStreamResource);
    }

    @GetMapping("cardlist")
    public String showCardList(){
        return "redirect:/onlycards/1";
    }

    @GetMapping("onlycards/{pageNr}")
    public String findPaginatedCardList(@PathVariable(value = "pageNr") int pageNr,
                                @RequestParam(name = "sortField",required = false,defaultValue = "cardName") String sortField,
                                @RequestParam(name = "sortDir",required = false,defaultValue = "asc") String sortDir,
                                @RequestParam(name="searchFilter",required = false,defaultValue = "") String searchFilter,
                                Model model){
        int pageSize = 12;
        Page<Card> page = cardService.findPaginated(pageNr,pageSize,sortField,sortDir,searchFilter);
        List<Card> cardList = page.getContent();

        model.addAttribute("currentPage", pageNr);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("searchFilter", searchFilter);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("cards", cardList);
        return "onlycards";
    }
}
