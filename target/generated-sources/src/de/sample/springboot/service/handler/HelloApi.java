package de.sample.springboot.service.handler;


import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2019-03-13T23:48:43.421+01:00")

@Api(value = "hello", description = "the hello API")
public interface HelloApi {

    @ApiOperation(value = "", notes = "Returns a greeting to the user!", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns the greeting.", response = String.class),
        @ApiResponse(code = 400, message = "Invalid characters in \"user\" were provided.", response = String.class) })
    @RequestMapping(value = "/hello/{user}",
        method = RequestMethod.GET)
    ResponseEntity<String> helloUserGet(
@ApiParam(value = "The name of the user to greet.",required=true ) @PathVariable("user") String user


);

}
