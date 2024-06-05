import javax.swing.text.*;

public class NumericDocumentFilter extends DocumentFilter {

    int limite;

    public NumericDocumentFilter(int limite) {
        this.limite = limite;
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        String currentText = getCurrentText(fb);
        String newText = new StringBuilder(currentText).replace(offset, offset + length, text).toString();

        if (isNumeric(newText) && newText.length() <= limite) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        String currentText = getCurrentText(fb);
        String newText = new StringBuilder(currentText).insert(offset, text).toString();

        if (isNumeric(newText) && newText.length() <= limite) {
            super.insertString(fb, offset, text, attr);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        String currentText = getCurrentText(fb);
        String newText = new StringBuilder(currentText).delete(offset, offset + length).toString();

        if (isNumeric(newText)) {
            super.remove(fb, offset, length);
        }
    }

    private boolean isNumeric(String text) {
        // Allow empty text
        if (text.isEmpty()) {
            return true;
        }

        // Allow only one decimal point
        if (text.chars().filter(ch -> ch == '.').count() > 1) {
            return false;
        }

        // Check if it's a valid double or if it ends with a decimal point
        try {
            if (text.endsWith(".")) {
                Double.parseDouble(text + "0");
            } else {
                Double.parseDouble(text);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String getCurrentText(FilterBypass fb) {
        try {
            Document doc = fb.getDocument();
            return doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
            return "";
        }
    }
}
