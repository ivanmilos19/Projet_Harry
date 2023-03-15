public enum Pet {
    OWL("You now have an owl"), CAT("You now have a cat"), RAT("You now have a rat"), TOAD("You now have a toad");
    private String message;
    Pet(String message) {
        this.message = message;
    }

    public String getPet (){
        return message;
    }


}