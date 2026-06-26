package gui;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class ChartForm extends JFrame {

    public ChartForm() {

        setTitle("Expense Analytics");
        setSize(700,500);
        setLocationRelativeTo(null);

        DefaultPieDataset dataset =
                new DefaultPieDataset();

        dataset.setValue("Food", 300);
        dataset.setValue("Transport", 150);
        dataset.setValue("Shopping", 200);
        dataset.setValue("Entertainment", 100);

        JFreeChart chart =
                ChartFactory.createPieChart(
                        "Expense Categories",
                        dataset,
                        true,
                        true,
                        false);

        ChartPanel panel =
                new ChartPanel(chart);

        setContentPane(panel);

        setVisible(true);
    }
}