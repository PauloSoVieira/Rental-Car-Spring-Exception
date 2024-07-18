package mindswap.Car.Api.message;

public enum Message {

    INVALID_NAME("Name can only contain letters"),
    USER_NOT_FOUND("User not found"),
    INVALID_EMAIL("Invalid email"),
    USER_NOT_FOUND_CAN_NOT_DELETE("User not found , can't delete"),
    USER_NOT_FOUND_CAN_NOT_EDIT("User not found , can't edit"),
    CAR_NOT_FOUND_CAN_NOT_UPDATE("Car not found , can't update"),
    CAR_NOT_FOUND_CAN_NOT_DELETE("Car not found , can't delete"),
    CAR_NOT_FOUND("Car not found"),
    INVALID_MODEL_OF_CAR("Invalid model of car"),
    INVALID_USER_ID("Invalid User ID"),
    INVALID_CAR_ID("Invalid car id"),
    INVALID_RENTAL_ID("Invalid Rental ID"),
    INVALID_RENTAL_DELETE("Can't Delete , ID not found"),
    INVALID_RENTAL_UPDATE("Can't update , ID not found"),
    USER_DOES_NOT_EXIST("User does not exists"),
    CAR_DOES_NOT_EXIST("Car does not exists");


    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
