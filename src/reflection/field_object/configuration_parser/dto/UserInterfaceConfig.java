package reflection.field_object.configuration_parser.dto;

import java.util.Arrays;

public class UserInterfaceConfig {
    private String titleColor;
    private String footerText;
    private short titleFontSize;
    private short footerFontSize;

    private String titleText;
    private String[] titleFonts;
    private short[] titleTextSizes;

    public String getTitleText() {
        return titleText;
    }

    public String[] getTitleFonts() {
        return titleFonts;
    }

    public short[] getTitleTextSizes() {
        return titleTextSizes;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public String getFooterText() {
        return footerText;
    }

    public short getTitleFontSize() {
        return titleFontSize;
    }

    public short getFooterFontSize() {
        return footerFontSize;
    }

    @Override
    public String toString() {
        return "UserInterfaceConfig{" +
                "titleColor='" + titleColor + '\'' +
                ", footerText='" + footerText + '\'' +
                ", titleFontSize=" + titleFontSize +
                ", footerFontSize=" + footerFontSize +
                ", titleText='" + titleText + '\'' +
                ", titleFonts=" + Arrays.toString(titleFonts) +
                ", titleTextSizes=" + Arrays.toString(titleTextSizes) +
                '}';
    }
}
