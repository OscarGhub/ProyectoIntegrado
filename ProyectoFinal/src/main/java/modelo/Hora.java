package modelo;

public enum Hora {
    HORA_06("06:00"), HORA_07("07:00"),
    HORA_08("08:00"), HORA_09("09:00"), HORA_10("10:00"), HORA_11("11:00"),
    HORA_12("12:00"), HORA_13("13:00"), HORA_14("14:00"), HORA_15("15:00"),
    HORA_16("16:00"), HORA_17("17:00"), HORA_18("18:00"), HORA_19("19:00");

    private final String horaTexto;

    Hora(String horaTexto) {
        this.horaTexto = horaTexto;
    }

    public String getHoraTexto() {
        return horaTexto;
    }

    @Override
    public String toString() {
        return horaTexto; // Esto lo usaremos en el ComboBox para mostrar la hora
    }
}
