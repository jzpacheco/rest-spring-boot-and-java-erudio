package br.com.jzpacheco.restspringbootandjavaerudio.controllers;


import br.com.jzpacheco.restspringbootandjavaerudio.services.BookServices;
import br.com.jzpacheco.restspringbootandjavaerudio.util.MediaType;
import br.com.jzpacheco.restspringbootandjavaerudio.vo.v1.BookVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "Endpoints for Managing Books")
public class BookController {

    @Autowired
    BookServices services;

    @GetMapping(value ="/{id}")
    @Operation(summary = "Finds a Book", description = "Finds a book by id",
            tags = "Books",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public BookVO findById(@PathVariable(value = "id") Integer id){
        return services.findById(id);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(summary = "Finds all books", description = "Finds all books",
            tags = "Books",
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = {
                        @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
                        )
                }),
                @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
                @ApiResponse(description = "Unathorized",responseCode = "401",content = @Content),
                @ApiResponse(description = "Not Found",responseCode = "404",content = @Content),
                @ApiResponse(description = "Internal Error",responseCode = "500",content = @Content)
            })
    public List<BookVO> findAll() {
        return services.findAll();
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(summary = "Adds a Book", description = "Adds a new Book by passing in a JSON, XML, or YML representation of the book.",
            tags = "Books",
            responses = {
                @ApiResponse(description = "Success", responseCode = "200",
                        content = @Content(schema = @Schema(implementation = BookVO.class))),
                @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
            })
    public BookVO create(@RequestBody BookVO book){

        return services.create(book);
    }

    @PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(summary = "Updates a Book", description = "Updates a book",
            tags = "Books",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
            })
    public BookVO update(@RequestBody BookVO book){
        return services.update(book);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Book", description = "Deletes a Book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") int id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
