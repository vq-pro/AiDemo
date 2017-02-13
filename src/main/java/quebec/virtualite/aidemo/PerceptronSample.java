package quebec.virtualite.aidemo;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

/**
 * This sample shows how to create, train, save and load simple Perceptron neural network
 */
public class PerceptronSample
{
    private static final String NEURAL_NET_FILE = "target/mySamplePerceptron.nnet";

    private final DataSet trainingSet;
    private final int nbInputs;
    private final int nbOutputs;

    public PerceptronSample(int nbInputs, int nbOutputs)
    {
        this.nbInputs = nbInputs;
        this.nbOutputs = nbOutputs;
        this.trainingSet = new DataSet(nbInputs, nbOutputs);
    }

    public void addTrainingData(double[] inputs, double output)
    {
        trainingSet.addRow(new DataSetRow(inputs, new double[] {output}));
    }

    public Double process(double[] inputs)
    {
        NeuralNetwork nnet = NeuralNetwork.createFromFile(NEURAL_NET_FILE);

        nnet.setInput(inputs);
        nnet.calculate();

        return nnet.getOutput()[0];
    }

    public void train()
    {
        NeuralNetwork myPerceptron = new Perceptron(nbInputs, nbOutputs);

        myPerceptron.learn(trainingSet);

        myPerceptron.save(NEURAL_NET_FILE);
    }
}