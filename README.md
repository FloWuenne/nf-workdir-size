# nf-workdir-size

A Nextflow plugin to monitor and report working directory sizes during pipeline execution.

## Requirements

- Nextflow `>=24.01.0-edge`

## Installation

To use this plugin in your Nextflow pipeline, you can add it to your pipeline configuration:

```
plugins {
  id 'nf-workdir-size@0.1.0'
}
```

## Description

This plugin helps monitor disk space usage by tracking the size of Nextflow's working directories after pipeline execution. 

## Components

### WorkdirSizeObserver

The `WorkdirSizeObserver` is a trace observer that:
- Reports the final size of working directories when the workflow completes

### WorkdirSizeFactory

The `WorkdirSizeFactory` is responsible for:
- Creating and registering the WorkdirSizeObserver
- Setting up the monitoring infrastructure
- Managing the lifecycle of the disk space monitoring

## Usage

Once the plugin is installed and enabled in your pipeline configuration, it will automatically track working directory sizes during pipeline execution.

The plugin will:
1. Initialize disk space monitoring when the workflow starts
2. Track working directory sizes for each process
3. Add information about workdir sizes to the .nextflow.log file

## Version

Current version: 0.1.0

## Support

For bug reports and feature requests, please open an issue on the [GitHub repository](https://github.com/FloWuenne/nf-workdir-size).