package cryst.blogapi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "username must be of more than four characters")
    private String name;

    @Email(message = "Email is not valid!")
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String about;
}
