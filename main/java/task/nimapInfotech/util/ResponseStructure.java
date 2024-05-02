package task.nimapInfotech.util;


import lombok.Data;

@Data
public class ResponseStructure<T> {

    private String message;
    private int status;
    private T data;

    public ResponseStructure() {
    }

    public ResponseStructure(String message, int status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
