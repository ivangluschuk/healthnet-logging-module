package logging.src.payload;

import lombok.Getter;
import lombok.Setter;

class UserResponse {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;
}
