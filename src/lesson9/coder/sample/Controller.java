package lesson9.coder.sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    @FXML
    private TextArea codeTEXTAREA;

    @FXML
    private ImageView mainImageView;

    @FXML
    private TextArea decodeTEXTAREA;

    private static Image imageNotChanged = null;
    private static Image imageReady = null;
    private static Color[][] colorsReady;
    private static Color[][] colorsNotChanged;
    private static final ArrayList<Character> LETTERS = new ArrayList<>();
    private static final char FIRST_FOR_ONE_SYMBOL = 'a';
    private static final char EMPTY_SYMBOL = 'b';
    private static String mainText = null;

    @FXML
    void initialize() {
        for (int i = 0; i < 26; i++) {
            LETTERS.add((char)((int)'a' + i));
        }

        for (int i = 0; i < 33; i++) {
            LETTERS.add((char) ((int) 'А' + i));
        }

        for (int i = 0; i < 26; i++) {
            LETTERS.add((char) ((int) 'а' + i));
        }

        LETTERS.add('\n');
        LETTERS.add('ї');
        LETTERS.add('Ї');
        LETTERS.add('є');
        LETTERS.add('Є');

        for (int i = 33; i <= 96; i++) {
            LETTERS.add((char)i);
        }

        LETTERS.add(' ');
        LETTERS.add('{');
        LETTERS.add('}');

    }

    @FXML
    private void codePressed() {
        if (!checkEnterClearness(codeTEXTAREA)) {
            try {
                System.out.println(codeTEXTAREA);
                colorsReady = codePicture(mainText);
                writeImage();
                mainText = null;
                showSuccessInfo("Your text successfully coded !");
            } catch (NullPointerException e) {
                codeTEXTAREA.clear();
                showNoImageError("No images to code in !");
            }
        }
    }

    @FXML
    private void decodePressed() {
        decodeTEXTAREA.clear();
        String decodedText = decodePicture(mainImageView.getImage());
        if (decodedText != null)
            decodeTEXTAREA.setText(normaliseString(decodedText));
    }

    @FXML
    private void quitPressed() {
        System.exit(0);
    }

    @FXML
    private void savePressed() {
        if (mainImageView.getImage() == null)
            return;
        File file = new File("RomeoProgram.png");
        for (int i = 1; true; i++) {
            if (!file.exists() && !file.isDirectory()) {
                try {
                    saveImage(mainImageView.getImage(), new FileOutputStream(file.getName()), "png");
                } catch (FileNotFoundException ignored) {
                }
                break;
            }
            file = new File("RomeoProgram(" + i + ").png");
        }
    }

    @FXML
    private void saveAsPressed() throws IOException {
        try {
            File file = saveAs();
            FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath());
            if (file.getName().endsWith("jpg"))
                saveImage(mainImageView.getImage(), outputStream, "jpg");
            else if (file.getName().endsWith("png"))
                saveImage(mainImageView.getImage(), outputStream, "png");
            else if (file.getName().endsWith("bmp"))
                saveImage(mainImageView.getImage(), outputStream, "bmp");
            outputStream.close();
        } catch (NullPointerException ignored) {
        }
    }

    private static boolean checkEnterClearness(TextArea textArea) {
        if (textArea.getText().equals("")) {
            textArea.setPromptText("Nothing to code");
            return true;
        } else {
            if (mainText == null)
                mainText = textArea.getText();
            textArea.clear();
            textArea.setPromptText("Wait for seconds...");
            textArea.clear();
            textArea.setPromptText("Enter text");
            return false;
        }
    }

    @FXML
    private void openTextPressed() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT document", "*.txt")
        );

        try {
            mainText = "";
            File file = chooser.showOpenDialog(new Stage());
            codeTEXTAREA.setText(file.getAbsolutePath());
            loadTextFile(file);
        } catch (IOException | NullPointerException ignored) {
        }
    }

    private static Color[][] codePicture(String text) {
        imageToPixels(imageNotChanged);
        Color[][] imageSecond = new Color[colorsNotChanged.length][colorsNotChanged[0].length];
        int lettersCounter = 0;
        int[] finalText = textToInt(text);
        System.out.println(Arrays.toString(finalText));
        String currentColor1, colorWithNoLast1, currentColor2, colorWithNoLast2, currentColor3, colorWithNoLast3;
        for (int i = 0; i < colorsNotChanged.length; i++) {
            for (int j = 0; j < colorsNotChanged[i].length; j = j + 3, lettersCounter++) {
                currentColor1 = String.valueOf(colorsNotChanged[i][j]);
                currentColor2 = String.valueOf(colorsNotChanged[i][j + 1]);
                currentColor3 = String.valueOf(colorsNotChanged[i][j + 2]);
                colorWithNoLast1 = currentColor1.substring(0, currentColor1.length() - 1);
                colorWithNoLast2 = currentColor2.substring(0, currentColor2.length() - 1);
                colorWithNoLast3 = currentColor3.substring(0, currentColor3.length() - 1);
                try {
                    if (String.valueOf(finalText[lettersCounter]).length() == 1) {
                        imageSecond[i][j] = Color.valueOf(colorWithNoLast1 + FIRST_FOR_ONE_SYMBOL);
                        imageSecond[i][j + 1] = Color.valueOf(colorWithNoLast2 + FIRST_FOR_ONE_SYMBOL);
                        imageSecond[i][j + 2] = Color.valueOf(colorWithNoLast3 + finalText[lettersCounter]);
                    } else if (String.valueOf(finalText[lettersCounter]).length() == 2) {
                        System.out.println(String.valueOf(finalText[lettersCounter]).charAt(0));
                        imageSecond[i][j] = Color.valueOf(colorWithNoLast1 + FIRST_FOR_ONE_SYMBOL);
                        imageSecond[i][j + 1] = Color.valueOf(colorWithNoLast2 + String.valueOf(finalText[lettersCounter]).charAt(0));
                        imageSecond[i][j + 2] = Color.valueOf(colorWithNoLast3 + String.valueOf(finalText[lettersCounter]).charAt(1));//змінити 1 на 2, поки що для перевірки
                    } else {
                        imageSecond[i][j] = Color.valueOf(colorWithNoLast1 + String.valueOf(finalText[lettersCounter]).charAt(0));
                        imageSecond[i][j + 1] = Color.valueOf(colorWithNoLast2 + String.valueOf(finalText[lettersCounter]).charAt(1));
                        imageSecond[i][j + 2] = Color.valueOf(colorWithNoLast3 + String.valueOf(finalText[lettersCounter]).charAt(2));
                    }
                    System.out.println(imageSecond[i][j]);
                    System.out.println(imageSecond[i][j + 1]);
                    System.out.println(imageSecond[i][j + 2]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    imageSecond[i][j] = Color.valueOf(colorWithNoLast1 + EMPTY_SYMBOL);
                    imageSecond[i][j + 1] = Color.valueOf(colorWithNoLast2 + EMPTY_SYMBOL);
                    imageSecond[i][j + 2] = Color.valueOf(colorWithNoLast3 + EMPTY_SYMBOL);
                }
            }
        }

        return imageSecond;
    }

    private static String decodePicture(Image image) {
        try {
            imageToPixels(image);
        } catch (RuntimeException e) {
            showNoImageError("No images found");
            return null;
        }
        int lettersCounter = 0;
        ArrayList<Character> finalCharsList = new ArrayList<>();
        String currentColor1, currentColor2, currentColor3, finalString, currentNumber;
        int currentInteger;
        char lastCharacter1, lastCharacter2, lastCharacter3;
        for (Color[] colors : colorsNotChanged) {
            for (int j = 0; j < colors.length; j = j + 3, lettersCounter++) {
                currentColor1 = String.valueOf(colors[j]);
                currentColor2 = String.valueOf(colors[j + 1]);
                currentColor3 = String.valueOf(colors[j + 2]);
                lastCharacter1 = currentColor1.charAt(currentColor1.length() - 1);
                lastCharacter2 = currentColor2.charAt(currentColor2.length() - 1);
                lastCharacter3 = currentColor3.charAt(currentColor3.length() - 1);

                if (lastCharacter1 == EMPTY_SYMBOL) {
                    finalString = finalCharsList.toString();
                    return finalString;
                } else if (lastCharacter1 == FIRST_FOR_ONE_SYMBOL) {
                    if (lastCharacter2 == FIRST_FOR_ONE_SYMBOL)
                        currentInteger = Integer.parseInt(String.valueOf(lastCharacter3));
                    else currentInteger = Integer.parseInt(new String(new char[]{lastCharacter2, lastCharacter3}));
                } else {
                    currentNumber = new String(new char[]{lastCharacter1, lastCharacter2, lastCharacter3});
                    try {
                        currentInteger = Integer.parseInt(currentNumber);
                    } catch (NumberFormatException e) {
                        System.out.println(currentNumber);
                        showNoTextInfo("There is no text in loaded image");
                        return null;
                    }
                }
                finalCharsList.add(LETTERS.get(currentInteger));
            }
        }

        finalString = finalCharsList.toString();
        return finalString;
    }

    private static int[] textToInt(String text) {
        int[] finalStatement = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < LETTERS.size(); j++) {
                if (LETTERS.get(j) == text.charAt(i))
                    finalStatement[i] = j;
            }
        }
        return finalStatement;
    }

    @FXML
    private void openImagePressed() {
        File openedFile = getFile();
        openImage(openedFile);
    }

    private void openImage(File file) {
        try {
            imageNotChanged = new Image(new FileInputStream(file.getAbsolutePath()));
            mainImageView.setImage(imageNotChanged);
            colorsNotChanged = new Color[(int) imageNotChanged.getWidth()][(int) imageNotChanged.getHeight()];
        } catch (Exception ignored) {
        }
    }

    private File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image to code in");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All mages", "*.jpg", "*.png", "*.bmp"),
                new FileChooser.ExtensionFilter("JPG Image", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG Image", "*.png"),
                new FileChooser.ExtensionFilter("BMP Image", "*.bmp")
        );
        return fileChooser.showOpenDialog(new Stage());
    }

    private static void imageToPixels(Image image) {
        PixelReader pixelReader = image.getPixelReader();
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                colorsNotChanged[i][j] = pixelReader.getColor(i, j);
                //System.out.println(colors[i][j]);
            }
        }
    }

    private void writeImage() {
        WritableImage wImage = new WritableImage((int) imageNotChanged.getWidth(), (int) imageNotChanged.getHeight());
        PixelWriter writer = wImage.getPixelWriter();
        for (int i = 0; i < imageNotChanged.getWidth(); i++) {
            for (int j = 0; j < imageNotChanged.getHeight(); j++) {
                writer.setColor(i, j, colorsReady[i][j]);
            }

        }

        mainImageView.setImage(new ImageView(wImage).getImage());
        System.out.println("OK");
    }

    private static String normaliseString(String inCorrectString) {
        String result = "";
        inCorrectString = inCorrectString.substring(1, inCorrectString.length() - 1);
        for (int i = 0; i < inCorrectString.length(); i += 3) {
            result = result.concat(String.valueOf(inCorrectString.charAt(i)));
        }
        return result;
    }

    private static void saveImage(Image image, FileOutputStream out, String format) {
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bufferedImage, format, out);
        } catch (IOException | NullPointerException ignored) {
        }
    }

    private static File saveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Saving dialog");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PNG Image", "*.png"));
        return fileChooser.showSaveDialog(new Stage());
    }

    private static void loadTextFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
        do {
            mainText = mainText.concat(reader.readLine() + "\n");
        } while (reader.readLine() != null);
        reader.close();
    }

    private static void showNoImageError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("There is no images loaded");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void showNoTextInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No text found");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void showSuccessInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successfully coded !");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void showFileNotFoundedError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File Error");
        alert.setHeaderText("File not founded");
        alert.showAndWait();
    }

    public void choseFileToSaveIn(ActionEvent actionEvent) {
        PrintWriter pw;
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Chose file to save text");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT document", "*.txt")
        );

        try {
            if (!decodeTEXTAREA.getText().equals("") || decodeTEXTAREA.getText() != null) {
                pw = new PrintWriter(chooser.showSaveDialog(new Stage()));
                pw.write(decodeTEXTAREA.getText());
                pw.close();
            }
        } catch (IOException | NullPointerException ignored){
        }
    }
}