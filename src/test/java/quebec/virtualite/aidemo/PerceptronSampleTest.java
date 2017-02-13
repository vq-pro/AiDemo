package quebec.virtualite.aidemo;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

public class PerceptronSampleTest
{
    @Test
    public void sample() throws Exception
    {
        // Given
        PerceptronSample perceptronSample = new PerceptronSample(2, 1);

        perceptronSample.addTrainingData(new double[]{
            0,
            0}, 0);
        perceptronSample.addTrainingData(new double[]{
            0,
            1}, 0);
        perceptronSample.addTrainingData(new double[]{
            1,
            0}, 0);
        perceptronSample.addTrainingData(new double[]{
            1,
            1}, 1);

        perceptronSample.train();

        // When
        assertThat(perceptronSample.process(new double[]{
            1.0,
            1.0}), closeTo(1.0d, 0.0002d));
        assertThat(perceptronSample.process(new double[]{
            0.0,
            0.0}), closeTo(0.0d, 0.0002d));
    }
}