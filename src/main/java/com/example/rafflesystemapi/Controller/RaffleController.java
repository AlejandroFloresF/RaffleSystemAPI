    package com.example.rafflesystemapi.Controller;

    import com.example.rafflesystemapi.ViewModel.Raffle;
    import com.example.rafflesystemapi.Service.RaffleService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.util.List;


    @RestController
    @RequestMapping(path = "api/v1/raffles")
    public class RaffleController {

        private final RaffleService raffleService;

        @Autowired
        public RaffleController(RaffleService raffleService) {
            this.raffleService = raffleService;
        }

        @GetMapping
        public List<Raffle> getAllRaffles() {
            return raffleService.getAllRaffles();
        }

        @PostMapping
        public ResponseEntity<Object> addRaffle(@RequestBody Raffle raffle) {
            return this.raffleService.newRaffle(raffle);
        }

        @PutMapping
        public ResponseEntity<Object> updateRaffle(@RequestBody Raffle raffle) {
            return this.raffleService.newRaffle(raffle);
        }

        @DeleteMapping(path = "{raffleId}")
        public ResponseEntity<Object> deleteRaffle(@PathVariable("raffleId") Long id) {
            return this.raffleService.deleteRaffle(id);
        }

        @PostMapping("/uploadBackgroundImage/{id}")
        public ResponseEntity<String> uploadImage(
                @PathVariable("id") Long id,
                @RequestParam("file") MultipartFile file
        ) {
            try {
                raffleService.saveBackgroundImage(id, file);
                return ResponseEntity.ok("Image Successfully uploaded");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Raffle not found with ID: " + id);
            }
        }

        @GetMapping("/available-tickets/{raffleId}")
        public List<Integer> getAvailableTickets(@PathVariable("raffleId") Long raffleId) {
            return raffleService.getAvailableTickets(raffleId);
        }

    }
