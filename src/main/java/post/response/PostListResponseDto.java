package post.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class PostListResponseDto {

    private UserDTO user;
    private List<PostResponseDto> postList;

    public PostListResponseDto(UserDTO user, List<PostResponseDto> postList) {
        this.user = user;
        this.postList = postList;
    }


}
