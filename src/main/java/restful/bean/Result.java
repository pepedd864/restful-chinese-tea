package restful.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result {
    private int code;
    private String description;
    private Object data;
    private String nextAction;

    public Result(int code, String description, Object data, String nextAction) {
        this.code = code;
        this.description = description;
        this.data = data;
        this.nextAction = nextAction;
    }

}
