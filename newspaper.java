import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class NewspaperCover extends JFrame {

    public NewspaperCover() {
        setTitle("The Morning Dispatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        NewspaperPanel panel = new NewspaperPanel();
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewspaperCover::new);
    }
}

class NewspaperPanel extends JPanel {

    private static final int W = 750;
    private static final int H = 1000;

    private static final Color INK    = new Color(15, 12, 8);
    private static final Color PAPER  = new Color(250, 244, 228);
    private static final Color CREAM  = new Color(240, 232, 210);
    private static final Color ACCENT = new Color(140, 20, 20);
    private static final Color GRAY   = new Color(100, 95, 88);

    // Column layout constants (previously scattered as magic numbers)
    private static final int COL_LEFT_MARGIN = 26;
    private static final int COL_WIDTH       = 185;
    private static final int COL_GAP         = 10;
    private static final int COL_COUNT       = 3;
    private static final int COL_Y_START     = 405;
    private static final int COL_Y_MAX       = 755;

    NewspaperPanel() {
        setPreferredSize(new Dimension(W, H));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,       RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,  RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,          RenderingHints.VALUE_RENDER_QUALITY);

        drawPaper(g2);
        drawMasthead(g2);
        drawMetaBar(g2);
        drawTopRule(g2);
        drawMainHeadline(g2);
        drawDeck(g2);
        drawByline(g2);
        drawColumnRule(g2);
        drawColumns(g2);
        drawImageBlock(g2);
        drawSidebar(g2);
        drawFooter(g2);
    }

    private void drawPaper(Graphics2D g2) {
        g2.setColor(PAPER);
        g2.fillRect(0, 0, W, H);
        GradientPaint gp = new GradientPaint(0, 0, new Color(255, 252, 240), W, H, new Color(238, 228, 205));
        g2.setPaint(gp);
        g2.fillRect(0, 0, W, H);
        g2.setColor(INK);
        g2.setStroke(new BasicStroke(3f));
        g2.drawRect(14, 14, W - 28, H - 28);
        g2.setStroke(new BasicStroke(1f));
        g2.drawRect(18, 18, W - 36, H - 36);
    }

    private void drawMasthead(Graphics2D g2) {
        g2.setColor(ACCENT);
        g2.fillRect(22, 22, W - 44, 5);
        g2.setColor(INK);
        g2.fillRect(22, 30, W - 44, 2);

        Font mastheadFont = new Font("Serif", Font.BOLD | Font.ITALIC, 72);
        g2.setFont(mastheadFont);
        g2.setColor(INK);
        String title = "The Morning Dispatch";
        FontMetrics fm = g2.getFontMetrics();
        int tx = (W - fm.stringWidth(title)) / 2;
        g2.drawString(title, tx, 110);

        Font tagFont = new Font("Serif", Font.ITALIC, 13);
        g2.setFont(tagFont);
        g2.setColor(GRAY);
        String tag = "\u201cAll the news that's fit to print — and then some\u201d";
        fm = g2.getFontMetrics();
        g2.drawString(tag, (W - fm.stringWidth(tag)) / 2, 128);

        g2.setColor(INK);
        g2.setStroke(new BasicStroke(2.5f));
        g2.drawLine(22, 136, W - 22, 136);
        g2.setStroke(new BasicStroke(0.8f));
        g2.drawLine(22, 141, W - 22, 141);
    }

    private void drawMetaBar(Graphics2D g2) {
        Font metaFont = new Font("SansSerif", Font.PLAIN, 11);
        g2.setFont(metaFont);
        g2.setColor(INK);
        FontMetrics fm = g2.getFontMetrics();

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")).toUpperCase();
        String vol   = "VOL. CLXXIII  \u2022  No. 214";
        String price = "PRICE: $1.50";

        g2.drawString(date,  26, 158);
        g2.drawString(vol,  (W - fm.stringWidth(vol))  / 2, 158);
        g2.drawString(price, W - 22 - fm.stringWidth(price), 158);

        g2.setColor(INK);
        g2.setStroke(new BasicStroke(1.2f));
        g2.drawLine(22, 163, W - 22, 163);
    }

    private void drawTopRule(Graphics2D g2) {
        g2.setColor(INK);
        g2.fillRect(22, 167, W - 44, 20);
        Font sectionFont = new Font("SansSerif", Font.BOLD, 10);
        g2.setFont(sectionFont);
        g2.setColor(PAPER);
        String[] sections = {"WORLD", "POLITICS", "SCIENCE", "ARTS", "BUSINESS", "SPORT", "OPINION"};
        int sx = 30;
        for (String s : sections) {
            g2.drawString(s, sx, 181);
            sx += g2.getFontMetrics().stringWidth(s) + 20;
        }
    }

    private void drawMainHeadline(Graphics2D g2) {
        Font headlineFont = new Font("Serif", Font.BOLD, 44);
        g2.setFont(headlineFont);
        g2.setColor(INK);
        String[] lines = {"Scientists Discover Hidden Ocean", "Beneath Antarctic Ice Sheet"};
        int y = 232;
        for (String line : lines) {
            g2.drawString(line, 26, y);
            y += 52;
        }
    }

    private void drawDeck(Graphics2D g2) {
        Font deckFont = new Font("Serif", Font.ITALIC, 17);
        g2.setFont(deckFont);
        g2.setColor(new Color(60, 55, 48));
        String deck = "A vast liquid reservoir, twice the size of the Caspian Sea, may hold clues to the origins of life";
        g2.drawString(deck, 26, 352);
        g2.setColor(INK);
        g2.setStroke(new BasicStroke(0.8f));
        g2.drawLine(26, 360, W - 22, 360);
    }

    private void drawByline(Graphics2D g2) {
        Font byFont = new Font("SansSerif", Font.BOLD, 10);
        g2.setFont(byFont);
        g2.setColor(GRAY);
        g2.drawString("BY ELEANOR HARTWELL  \u2022  SCIENCE CORRESPONDENT", 26, 376);
        Font locFont = new Font("SansSerif", Font.PLAIN, 10);
        g2.setFont(locFont);
        g2.drawString("MCMURDO STATION, ANTARCTICA — Monday, " +
            LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM d")), 26, 390);
    }

    private void drawColumnRule(Graphics2D g2) {
        g2.setColor(INK);
        g2.setStroke(new BasicStroke(1f));
        int[] dividers = {26 + 195, 26 + 390};
        int yTop = 400, yBot = 760;
        for (int x : dividers) {
            g2.drawLine(x, yTop, x, yBot);
        }
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(26 + 440, yTop, 26 + 440, yBot);
    }

    /**
     * Wraps a block of text to fit within maxWidth, using the currently
     * selected font on g2. Returns the wrapped lines in order.
     */
    private List<String> wrapText(Graphics2D g2, String text, int maxWidth) {
        FontMetrics fm = g2.getFontMetrics();
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        for (String word : text.split(" ")) {
            String test = line.length() == 0 ? word : line + " " + word;
            if (fm.stringWidth(test) > maxWidth) {
                if (line.length() > 0) {
                    lines.add(line.toString());
                }
                line = new StringBuilder(word);
            } else {
                line = new StringBuilder(test);
            }
        }
        if (line.length() > 0) {
            lines.add(line.toString());
        }
        return lines;
    }

    private void drawColumns(Graphics2D g2) {
        String[] paragraphs = {
            "An international team of glaciologists announced yesterday what may be the most significant geographical discovery of the century — a massive subglacial ocean hidden beneath nearly three kilometres of Antarctic ice.",
            "The body of water, named Lake Erebus by the research team, spans approximately 430,000 square kilometres and reaches depths of over 800 metres at its centre. Its discovery was made possible by a new generation of ice-penetrating radar developed jointly by MIT and the British Antarctic Survey.",
            "\"We had theoretical models suggesting liquid water could exist at these pressures,\" said lead researcher Dr. Amara Singh of the University of Edinburgh. \"But the scale of what we found exceeded every projection we had made.\"",
            "The lake has been isolated from the surface for an estimated 25 million years, raising extraordinary possibilities for the existence of microbial life entirely unknown to science. Thermal vents detected along the lake floor suggest a sustained energy source capable of supporting complex ecosystems.",
            "The discovery has already drawn comparisons to Europa, Jupiter's moon, which scientists believe harbours a similar subsurface ocean. Astrobiologists say the findings offer a compelling analogue for the search for extraterrestrial life.",
            "Drilling expeditions to retrieve water samples are expected to commence within 18 months, pending review by the Antarctic Treaty Committee."
        };

        Font bodyFont = new Font("Serif", Font.PLAIN, 12);
        g2.setFont(bodyFont);
        g2.setColor(INK);
        FontMetrics fm = g2.getFontMetrics();
        int lineHeight = fm.getHeight() + 1;

        int col = 0;
        int x = COL_LEFT_MARGIN + col * (COL_WIDTH + COL_GAP);
        int y = COL_Y_START;

        paragraphLoop:
        for (String para : paragraphs) {
            for (String line : wrapText(g2, para, COL_WIDTH - 4)) {
                if (y + lineHeight > COL_Y_MAX) {
                    col++;
                    if (col >= COL_COUNT) {
                        // No more columns available — stop rendering rather
                        // than drawing off the page.
                        break paragraphLoop;
                    }
                    x = COL_LEFT_MARGIN + col * (COL_WIDTH + COL_GAP);
                    y = COL_Y_START;
                }
                g2.drawString(line, x + 4, y);
                y += lineHeight;
            }
            y += 6; // paragraph gap
        }
    }

    private void drawImageBlock(Graphics2D g2) {
        int ix = 26, iy = 760, iw = W - 52, ih = 130;
        g2.setColor(new Color(200, 192, 175));
        g2.fillRect(ix, iy, iw, ih);

        GradientPaint sky = new GradientPaint(ix, iy, new Color(60, 90, 130), ix, iy + 70, new Color(140, 170, 200));
        g2.setPaint(sky);
        g2.fillRect(ix, iy, iw, 70);

        g2.setColor(new Color(220, 235, 245));
        int[] xp = {ix, ix + 80, ix + 160, ix + 280, ix + 380, ix + 500, ix + iw};
        int[] yp = {iy + 70, iy + 65, iy + 72, iy + 63, iy + 68, iy + 64, iy + 70};
        g2.fillPolygon(xp, yp, xp.length);
        g2.setColor(new Color(235, 245, 255));
        g2.fillRect(ix, iy + 70, iw, ih - 70);

        g2.setColor(new Color(40, 40, 50));
        g2.fillRect(ix + 320, iy + 50, 60, 20);
        g2.fillRect(ix + 340, iy + 38, 20, 12);
        g2.fillRect(ix + 355, iy + 30, 3, 10);

        g2.fillRect(ix + 560, iy + 40, 8, 30);
        int[] rx = {ix + 552, ix + 576, ix + 564};
        int[] ry = {iy + 40, iy + 40, iy + 25};
        g2.fillPolygon(rx, ry, 3);

        g2.setColor(new Color(0, 0, 0, 120));
        g2.fillRect(ix, iy, 200, 20);
        Font labelFont = new Font("SansSerif", Font.BOLD, 10);
        g2.setFont(labelFont);
        g2.setColor(PAPER);
        g2.drawString("MCMURDO RESEARCH BASE — FILE PHOTO / AP", ix + 5, iy + 13);

        g2.setColor(INK);
        g2.setStroke(new BasicStroke(1f));
        g2.drawLine(ix, iy + ih, ix + iw, iy + ih);
        Font captionFont = new Font("Serif", Font.ITALIC, 11);
        g2.setFont(captionFont);
        g2.setColor(GRAY);
        String caption = "The McMurdo Station, Antarctica, from which the research team launched their groundbreaking ice-penetrating radar survey last autumn.";
        int cy = iy + ih + 14;
        for (String line : wrapText(g2, caption, iw)) {
            g2.drawString(line, ix, cy);
            cy += g2.getFontMetrics().getHeight();
        }
    }

    private void drawSidebar(Graphics2D g2) {
        int sx = 26 + 445, sy = 400, sw = W - 22 - sx, sh = 355;
        g2.setColor(CREAM);
        g2.fillRect(sx, sy, sw, sh);
        g2.setColor(INK);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRect(sx, sy, sw, sh);

        g2.setColor(INK);
        g2.fillRect(sx, sy, sw, 22);
        Font sideHead = new Font("SansSerif", Font.BOLD, 11);
        g2.setFont(sideHead);
        g2.setColor(PAPER);
        g2.drawString("TODAY'S BRIEFS", sx + 6, sy + 15);

        String[][] briefs = {
            {"ECONOMY", "Central bank holds rates steady amid cooling inflation data; markets respond with cautious optimism."},
            {"HEALTH", "WHO approves new malaria vaccine for children under five — a milestone in global public health."},
            {"SPACE", "NASA's Artemis IV crew completes lunar surface training at Hawaii lava fields ahead of 2027 mission."},
            {"CULTURE", "The Louvre announces discovery of an unsigned Caravaggio portrait in its archival collection."},
        };

        int by = sy + 36;
        Font briefHead = new Font("SansSerif", Font.BOLD, 10);
        Font briefBody = new Font("Serif", Font.PLAIN, 11);

        for (String[] brief : briefs) {
            g2.setColor(ACCENT);
            g2.setFont(briefHead);
            g2.drawString(brief[0], sx + 6, by);
            by += 14;

            g2.setColor(INK);
            g2.setFont(briefBody);
            for (String line : wrapText(g2, brief[1], sw - 12)) {
                g2.drawString(line, sx + 6, by);
                by += g2.getFontMetrics().getHeight();
            }
            by += 10;

            g2.setColor(new Color(180, 170, 150));
            g2.setStroke(new BasicStroke(0.7f));
            g2.drawLine(sx + 4, by - 4, sx + sw - 4, by - 4);
        }
    }

    private void drawFooter(Graphics2D g2) {
        int fy = 910;
        g2.setColor(INK);
        g2.setStroke(new BasicStroke(2f));
        g2.drawLine(22, fy, W - 22, fy);
        g2.setStroke(new BasicStroke(0.8f));
        g2.drawLine(22, fy + 4, W - 22, fy + 4);

        Font footFont = new Font("SansSerif", Font.PLAIN, 10);
        g2.setFont(footFont);
        g2.setColor(GRAY);
        FontMetrics fm = g2.getFontMetrics();

        String left  = "FOUNDED 1852 — PRINTED IN NEW YORK CITY";
        String right = "SUBSCRIBE: dispatch.com/subscribe";
        String mid   = "\u00a9 " + LocalDate.now().getYear() + " THE MORNING DISPATCH. ALL RIGHTS RESERVED.";

        g2.drawString(left,  26, fy + 18);
        g2.drawString(mid,  (W - fm.stringWidth(mid)) / 2, fy + 18);
        g2.drawString(right, W - 22 - fm.stringWidth(right), fy + 18);

        g2.setColor(INK);
        Font pgFont = new Font("Serif", Font.BOLD | Font.ITALIC, 13);
        g2.setFont(pgFont);
        String pgStr = "Page 1 — Section A";
        g2.drawString(pgStr, (W - g2.getFontMetrics().stringWidth(pgStr)) / 2, fy + 38);
    }
}